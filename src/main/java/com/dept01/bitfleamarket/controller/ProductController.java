package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.json.*;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.utils.OBSUploadUtils;
import com.dept01.bitfleamarket.utils.Result;
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
    @Autowired
    private OBSUploadUtils obsUploadUtils;
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
        String userIdStr = httpRequest.getHeader("userId");
        if (userIdStr == null || userIdStr.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            return Result.error((short) 1, "Invalid user ID", null);
        }

        return productService.createProduct(request, userId);
    }

    @PostMapping("/product/{product_id}/uploadPic")
    public Result uploadProductImage(@PathVariable("product_id") int productId,
                                     @RequestParam("img") MultipartFile img) throws IOException  {
        Map<String, Object> returnMap = obsUploadUtils.uploadProductImage(img);
        return productService.uploadProductImage(productId, img);
    }


//    //发布商品
//    @PostMapping("/products")
//    public String publishProduct() {
//        return "publish";
//    }
    @PutMapping("/product/{product_id}")
    public Result updateProduct(@PathVariable("product_id") int productId,
                                @RequestBody UpdateProductRequest request,
                                HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        String userIdStr = httpRequest.getHeader("user_id");
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
    @PutMapping("/product/{product_id}/status")
    public Result updateProductStatus(@PathVariable("product_id") int productId,
                                      @RequestBody String status,
                                      HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        String userIdStr = httpRequest.getHeader("Authorization");
        if (userIdStr == null || userIdStr.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            return Result.error((short) 1, "Invalid user ID", null);
        }

        return productService.updateProductStatus(productId, status, userId);
    }

    @GetMapping("/product/{user_id}")
    public Result getUserProducts(@PathVariable("user_id") int userId,
                                  @RequestBody UserProductsRequest request,
                                  HttpServletRequest httpRequest) {
        // 检查请求头中的 Authorization
        String authorization = httpRequest.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            return Result.error((short) 6, "Unauthorized", null);
        }

        return productService.getUserProducts(userId, request.getLastProductId(), request.getNum());
    }
}
