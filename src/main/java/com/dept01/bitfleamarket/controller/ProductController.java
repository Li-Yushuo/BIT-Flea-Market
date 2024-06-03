package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.Service.ProductService;
import com.dept01.bitfleamarket.json.GetProductsReturn;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    //获取商品列表
    @GetMapping("/products")
    public Result getProducts(
            int offset, int num, String search_input, String product_category, int price_choice) {
        GetProductsReturn products = productService.getProducts(offset, num, search_input, product_category, price_choice);
        if (products.getTotal_num() == 0)
            return Result.error("无符合条件的商品");
        else
            return Result.success(products);
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
