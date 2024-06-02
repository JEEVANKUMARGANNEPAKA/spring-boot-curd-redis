package com.jeevankumar.spring_boot_curd_redis.repository;

import com.jeevankumar.spring_boot_curd_redis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
