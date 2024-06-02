package com.dept01.bitfleamarket.pojo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    private int imageId;
    private int productId;
    private String imageUrl;
    private LocalDateTime createTime;
}