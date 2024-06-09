package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.json.*;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.utils.JwtUtils;
import com.dept01.bitfleamarket.utils.OBSUploadUtils;
import com.dept01.bitfleamarket.utils.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //获取商品列表
    @GetMapping("/products")
    public Result getProducts(
            @RequestParam int offset,
            @RequestParam int num,
            @RequestParam(required = false) String search_input,
            @RequestParam(required = false) String product_category,
            @RequestParam(required = false) int price_choice
    ) {
        Result result = productService.getProducts(offset, num, search_input, product_category, price_choice);
        return result;
    }

    //获取商品详情
    @GetMapping("/products/{product_id}")
    public Result getProductDetail(@PathVariable String product_id) {
        GetProductByIdReturn product = productService.getProductById(product_id);
        if (product == null)
            return Result.error("该商品不存在");
        else
            return Result.success(product);
    }

    @PostMapping("/products")
    public Result createProduct(@RequestBody CreateProductRequest request, HttpServletRequest httpRequest) {
        // 获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        String  userIdStr = jwt_claims.get("UserId", Integer.class).toString();

        if (userIdStr == null || userIdStr.isEmpty()) {
            return Result.error((short) 131, "Unauthorized", null);
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            return Result.error((short) 131, "Invalid user ID", null);
        }
        return productService.createProduct(request, userId);
    }

    @PostMapping("/product/uploadPic")
    public Result uploadProductImage(@RequestParam("img") MultipartFile img) throws IOException  {

        return productService.uploadProductImage(img);
    }


//    //发布商品
//    @PostMapping("/products")
//    public String publishProduct() {
//        return "publish";
//    }
    @PostMapping ("/products/{product_id}")
    public Result updateProduct(@PathVariable("product_id") int productId,
                                @RequestBody UpdateProductRequest request,
                                HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        // 获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        String  userIdStr = jwt_claims.get("UserId", Integer.class).toString();

        if (userIdStr == null || userIdStr.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            return Result.error((short) 131, "用户认证失败", null);
        }

        return productService.updateProduct(productId, request, userId);
    }
    @PostMapping("/products/{product_id}/status")
    public Result updateProductStatus(@PathVariable("product_id") int productId,

                                      HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        // 获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        String  userIdStr = jwt_claims.get("UserId", Integer.class).toString();

        if (userIdStr == null || userIdStr.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            return Result.error((short) 1, "Invalid user ID", null);
        }

        return productService.updateProductStatus(productId, "sold-out", userId);
    }

    @GetMapping("/products/users/{user_id}")
    public Result getUserProducts(@PathVariable("user_id") int userId,
                                  @RequestParam int last_product_id,
                                  @RequestParam int num,
                                  HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        return productService.getUserProducts(userId, last_product_id, num);
    }
}
