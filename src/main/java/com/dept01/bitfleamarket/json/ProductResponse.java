package com.dept01.bitfleamarket.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String product_id;
    private String name;
    private BigDecimal price;
    private String purchase_method; // 在数据库中为enum类型
    private String cover_image_url;
    private String product_category; // 在数据库中为enum类型
    private LocalDateTime created_time;
    private LocalDateTime updated_time;
    private String status; // 在数据库中为enum类型
}
