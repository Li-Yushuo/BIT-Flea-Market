package com.dept01.bitfleamarket.pojo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLabel {
    private Integer labelId;
    private Integer productId;
    private String name;
    private LocalDateTime createTime;
}