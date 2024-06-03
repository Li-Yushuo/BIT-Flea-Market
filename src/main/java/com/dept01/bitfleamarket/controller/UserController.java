package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //获取个人信息
    @GetMapping("/users/{user_id}")
    public Result getUserInfo() {

        return Result.success();
    }

    //修改个人信息
    @PutMapping("/users/{user_id}")
    public String modifyUserInfo() {
        return "modify";
    }
}
