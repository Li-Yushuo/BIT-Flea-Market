package com.dept01.bitfleamarket.pojo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int adminId;
    private String password;
    private String name;
    private LocalDateTime createTime;
}