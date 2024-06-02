package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

    //获取商品列表
    @GetMapping("/products")
    public String getProducts() {
        return "products";
    }

    //获取商品详情
    @GetMapping("/products/{product_id}")
    public String getProductDetail() {
        return "product";
    }

    //发布商品
    @PostMapping("/products")
    public String publishProduct() {
        return "publish";
    }

    //修改商品描述
    @PostMapping("/products/{product_id}")
    public String modifyProduct() {
        return "modify";
    }

    //修改商品状态
    @PostMapping("/products/{product_id}/status")
    public String modifyProductStatus() {
        return "modify_status";
    }

    //查看发布商品记录
    @GetMapping("/products/publish")
    public String getPublishRecord() {
        return "publish_record";
    }
}
