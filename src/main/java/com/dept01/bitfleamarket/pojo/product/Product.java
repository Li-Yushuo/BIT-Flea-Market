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
    private Integer productId;
    private String name;
    private BigDecimal price;
    private String purchaseMethod; // 在数据库中为enum类型
    private String productCategory; // 在数据库中为enum类型
    private Integer publisherId;
    private String status; // 在数据库中为enum类型
    private Integer inventory;
    private String description;
    // 2024-6-4 更改为Integer类型，0表示不匿名，1表示匿名
    private Integer isAnonymous;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public void setAnonymous(Integer isAnonymous) {
        isAnonymous = 1;
    }
}