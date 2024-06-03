package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    //冻结用户
    @PutMapping("/users/{user_id}/freeze")
    public String freezeUser() {

        return "freeze";
    }

    //解冻用户
    @PutMapping("/users/{user_id}/unfreeze")
    public String unfreezeUser() {
        return "unfreeze";
    }

    //搜索用户
    @GetMapping("/users")
    public String searchUser() {
        return "search";
    }

    //冻结商品
    @PutMapping("/products/{product_id}/freeze")
    public String freezeProduct() {
        return "freeze";
    }

    //解冻商品
    @PutMapping("/products/{product_id}/unfreeze")
    public String unfreezeProduct() {
        return "unfreeze";
    }

    //搜索商品
    @GetMapping("/products")
    public String searchProduct() {
        return "search";
    }

    //浏览举报信息
    @GetMapping("/reports")
    public String getReports() {
        return "reports";
    }
}
