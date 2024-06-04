package com.dept01.bitfleamarket.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String bitId;
    private String name;
    private String gender; // 在数据库中为enum类型
    private String password;
    private String contactInfo;
    private String personalSignature;
    private String avatarUrl;
    private String state; // 在数据库中为enum类型
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}