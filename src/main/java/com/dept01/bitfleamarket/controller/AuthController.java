package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.json.AuthInfoReturn;
import com.dept01.bitfleamarket.json.LoginRequest;
import com.dept01.bitfleamarket.json.RegisterRequest;
import com.dept01.bitfleamarket.json.VerifyRequest;
import com.dept01.bitfleamarket.service.AuthService;
import com.dept01.bitfleamarket.service.EmailService;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;

    //登录
    @PostMapping("/users/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        AuthInfoReturn authInfoReturn = authService.login(loginRequest.getBit_id(),loginRequest.getPassword());
        if (!Objects.equals(authInfoReturn.getInfo(), "登录成功")){
            return Result.error(authInfoReturn.getUser_id(),"登录失败",authInfoReturn.getInfo());
        }
        return Result.success(authInfoReturn);
    }

    //注册
    @PostMapping("/users/register")
    public Result register(@RequestBody RegisterRequest registerRequest) {
        AuthInfoReturn authInfoReturn = authService.register(registerRequest.getBit_id(),registerRequest.getPassword(),registerRequest.getVerification_code());
        if (!Objects.equals(authInfoReturn.getInfo(), "注册成功")){
            return Result.error( authInfoReturn.getUser_id(),"注册失败",authInfoReturn.getInfo());
        }
        return Result.success(authInfoReturn);
    }

    //忘记密码（修改密码）
    @PostMapping("/users/modify_password")
    public Result modify_password(@RequestBody RegisterRequest registerRequest) {
        AuthInfoReturn authInfoReturn = authService.modifyPassword(registerRequest.getBit_id(),registerRequest.getPassword(),registerRequest.getVerification_code());
        if (!Objects.equals(authInfoReturn.getInfo(), "修改成功")){
            return Result.error( 1,"修改失败",authInfoReturn.getInfo());
        }
        return Result.success(authInfoReturn);
    }

    //验证(邮箱验证)
    @PostMapping("/verify")
    public Result verify(@RequestBody VerifyRequest verifyRequest) {
        return emailService.sendVerificationCode(verifyRequest.getBit_id());
    }

}
