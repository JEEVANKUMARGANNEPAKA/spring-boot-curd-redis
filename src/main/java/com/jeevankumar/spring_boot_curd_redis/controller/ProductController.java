package com.jeevankumar.spring_boot_curd_redis.controller;

import com.jeevankumar.spring_boot_curd_redis.entity.Product;
import com.jeevankumar.spring_boot_curd_redis.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/saveProduct")
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
       Product savedProduct = this.productService.saveProduct(product);
       return savedProduct != null ?
               ResponseEntity.status(HttpStatus.CREATED).body("product is saved successfully with product name: " + product.getProductName()):
               ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(value = "/findAllProducts")
    public ResponseEntity<?> findAllOProducts(){
        List<Product> products = this.productService.findAllProducts();
        return products.size() >= 1 ?
                ResponseEntity.status(HttpStatus.OK).body(products):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/findProduct/{id}")
    public ResponseEntity<?> findProductById(@PathVariable(value = "id") Integer productId){
        Optional<Product> product = Optional.ofNullable(this.productService.findProductById(productId));
        return product.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(product):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable(value = "id") Integer productId){
        Product deleteProduct = this.productService.deleteProductById(productId);
        return deleteProduct != null ?
                ResponseEntity.status(HttpStatus.OK).body("Delete product with id: " + deleteProduct.getId()):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
