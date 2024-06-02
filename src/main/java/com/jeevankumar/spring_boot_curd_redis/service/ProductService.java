package com.jeevankumar.spring_boot_curd_redis.service;

import com.jeevankumar.spring_boot_curd_redis.entity.Product;
import com.jeevankumar.spring_boot_curd_redis.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product saveProduct(Product product){
        this.productRepository.save(product);
        return product;
    }

    @Cacheable(value = "product")
    public List<Product> findAllProducts(){
        return this.productRepository.findAll();
    }

    @Cacheable(value = "product",key = "#id",condition = "#id > 10")
    public Product findProductById(Integer productId){
        Optional<Product> optionalProduct = Optional.ofNullable(this.productRepository.findById(productId).orElseThrow(
                () -> new IllegalIdentifierException("There is no resouce for the id " + productId)));
        if (optionalProduct.isPresent())
            return optionalProduct.get();
        return null;
    }
    @Caching(
            evict = {@CacheEvict(value = "product", allEntries = true), @CacheEvict(value = "product", key = "#productId")
            })
    public Product deleteProductById(Integer productId) {
        Optional<Product> optionalProduct = Optional.ofNullable(this.productRepository.findById(productId).orElseThrow(
                () -> new IllegalIdentifierException("There is no resouce for the id " + productId)));
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        return null;
    }
}
