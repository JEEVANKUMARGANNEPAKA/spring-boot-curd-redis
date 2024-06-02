package com.jeevankumar.spring_boot_curd_redis.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_table")
public class Product implements Serializable {
    private static final long serialVersionUID = 1234567789L;
    @Id
    private Integer id;
    private String productName;
    private Integer productQty;
    private BigDecimal productPrice;
}
