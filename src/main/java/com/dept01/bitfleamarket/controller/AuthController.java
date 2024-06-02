package com.dept01.bitfleamarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/hello")
    public  String hello() {
        return "Hello, World!";
    }

    @PostMapping("/users/login")
    public String login() {
        return "login";
    }

    @PostMapping("/users/register")
    public String register() {
        return "register";
    }

    @PostMapping("/users/modify_password")
    public String modify_password() {
        return "modify_password";
    }

    @PostMapping("/verify")
    public String verify() {
        return "verify";
    }

}
