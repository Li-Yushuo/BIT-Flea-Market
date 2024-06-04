package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.json.AuthInfoReturn;
import com.dept01.bitfleamarket.json.LoginRequest;
import com.dept01.bitfleamarket.json.RegisterRequest;
import com.dept01.bitfleamarket.json.VerifyRequest;
import com.dept01.bitfleamarket.service.AuthService;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    //登录
    @PostMapping("/users/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        return Result.success(authService.login(loginRequest.getBit_id(),loginRequest.getPassword()));
    }

    //注册
    @PostMapping("/users/register")
    public Result register(@RequestBody RegisterRequest registerRequest) {
        return Result.success(authService.register(registerRequest.getBit_id(),registerRequest.getPassword(),registerRequest.getVerification_code()));
    }

    //忘记密码（修改密码）
    @PostMapping("/users/modify_password")
    public String modify_password() {
        return "modify_password";
    }

    //验证(邮箱验证)
    @PostMapping("/verify")
    public Result verify(@RequestBody VerifyRequest verifyRequest) {
        return Result.success();
    }

}
