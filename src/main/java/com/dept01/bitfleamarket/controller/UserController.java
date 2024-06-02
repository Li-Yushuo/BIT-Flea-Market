package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //获取个人信息
    @GetMapping("/users/{user_id}")
    public String getUserInfo() {
        return "user";
    }

    //修改个人信息
    @PutMapping("/users/{user_id}")
    public String modifyUserInfo() {
        return "modify";
    }
}
