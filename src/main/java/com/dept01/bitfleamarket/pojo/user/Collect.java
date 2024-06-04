package com.dept01.bitfleamarket.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collect {
    private Integer userId;
    private Integer productId;
    private LocalDateTime collectTime;
}