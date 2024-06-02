package com.dept01.bitfleamarket.pojo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId;
    private String name;
    private BigDecimal price;
    private String purchaseMethod; // 在数据库中为enum类型
    private String productCategory; // 在数据库中为enum类型
    private int publisherId;
    private String status; // 在数据库中为enum类型
    private int inventory;
    private String description;
    private boolean isAnonymous;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}