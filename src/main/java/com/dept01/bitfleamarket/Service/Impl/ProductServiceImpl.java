package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.json.*;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.mapper.product.ProductImageMapper;
import com.dept01.bitfleamarket.mapper.product.ProductMapper;
import com.dept01.bitfleamarket.mapper.user.UserMapper;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.product.ProductImage;
import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.utils.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    ProductImageMapper productImageMapper;
    UserMapper userMapper;

    @Override
    public GetProductsReturn getProducts(int offset, int num, String search_input, String product_categroy, int price_choice) {
        List<Product> allProducts = productMapper.selectAll();
        // 异常情况
        if (allProducts == null || allProducts.isEmpty() || offset < 0 || offset >= allProducts.size() || num <= 0) {
            List<Product> tmp = new ArrayList<>();
            GetProductsReturn getProductsReturn = new GetProductsReturn(0, tmp);
            return getProductsReturn;
        }
        // 正常情况
        else{
            // 计算子列表的结束下标
            int endIndex = Math.min(offset + num, allProducts.size());
            List<Product> tmp = new ArrayList<>(allProducts.subList(offset, endIndex));
            GetProductsReturn getProductsReturn = new GetProductsReturn(allProducts.size(), tmp);
            return getProductsReturn;
        }
    }

    @Override
    public GetProductByIdReturn getProductById(String id){
        Product product = productMapper.selectByProductId(Integer.parseInt(id));
        // 找不到就直接返回null
        if (product == null)
            return null;
        int publisherId = product.getPublisherId();
        User user = userMapper.selectByUserId(publisherId);
        List<ProductImage> productImageList = productImageMapper.getProductImagesByProductId(product.getProductId());
        return GetProductByIdReturn.from(product, user, productImageList);
    }

    @Override
    public Result createProduct(CreateProductRequest request, int userId) {
        LocalDateTime now = LocalDateTime.now();

        // 创建 Product 对象
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setPurchaseMethod(request.getPurchaseMethod());
        product.setProductCategory(request.getProductCategory());
        product.setPublisherId(userId);
        product.setStatus("ACTIVE"); // 假设新创建的产品状态为 ACTIVE
        product.setInventory(request.getInventory());
        product.setDescription(request.getDescription());
        product.setAnonymous(request.getIsAnonymous());
        product.setCreateTime(now);
        product.setUpdateTime(now);

        // 插入 Product 对象到数据库
        int rows = productMapper.insertProduct(product);
        if (rows <= 0) {
            return Result.error("Failed to create product");
        }

        // 获取生成的 productId
        int productId = product.getProductId();

        // 插入 ProductImage 对象到数据库
        for (CreateProductRequest.ImageRequest imageRequest : request.getImages()) {
            ProductImage productImage = new ProductImage();
            productImage.setProductId(productId);
            productImage.setImageUrl(imageRequest.getImageUrl());
            productImage.setCreateTime(now);
            productImageMapper.insertProductImage(productImage);
        }

        return Result.success(productId);
    }
    @Override
    public Result uploadProductImage(int productId, MultipartFile img) {
        if (img.isEmpty()) {
            return Result.error("Uploaded file is empty");
        }

        // 生成唯一的文件名
        String fileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();

        // 保存文件到指定目录（假设这里是项目的某个目录）
        String uploadDir = "uploads/";
        File file = new File(uploadDir + fileName);
        try {
            img.transferTo(file);
        } catch (IOException e) {
            return Result.error("Failed to save uploaded file");
        }

        // 保存文件信息到数据库
        ProductImage productImage = new ProductImage();
        productImage.setProductId(productId);
        productImage.setImageUrl(fileName); // 假设保存相对路径
        productImage.setCreateTime(LocalDateTime.now());

        productImageMapper.insertProductImage(productImage);

        return Result.success(fileName);
    }
    @Override
    public Result updateProduct(int productId, UpdateProductRequest request, int userId) {
        // 检查产品是否存在
        Product existingProduct = productMapper.selectByProductId(productId);
        if (existingProduct == null) {
            return Result.error("Product not found");
        }

        // 检查用户是否是发布者
        if (existingProduct.getPublisherId() != userId) {
            return Result.error((short) 7, "The product is not published by this user", null);
        }

        // 检查产品状态
        if ("FROZEN".equals(existingProduct.getStatus()) || "OFF_SHELF".equals(existingProduct.getStatus())) {
            return Result.error((short) 8, "The product is frozen or off the shelf", null);
        }

        // 更新产品信息
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }
        if (request.getPrice() != null) {
            existingProduct.setPrice(request.getPrice());
        }
        if (request.getPurchaseMethod() != null) {
            existingProduct.setPurchaseMethod(request.getPurchaseMethod());
        }
        if (request.getProductCategory() != null) {
            existingProduct.setProductCategory(request.getProductCategory());
        }
        if (request.getInventory() != null) {
            existingProduct.setInventory(request.getInventory());
        }
        if (request.getDescription() != null) {
            existingProduct.setDescription(request.getDescription());
        }
        if (request.getIsAnonymous() != null) {
            existingProduct.setAnonymous(request.getIsAnonymous());
        }

        existingProduct.setUpdateTime(LocalDateTime.now());

        int rows = productMapper.update(existingProduct);
        if (rows <= 0) {
            return Result.error("Failed to update product");
        }

        return Result.success(existingProduct);
    }

    @Override
    public Result updateProductStatus(int productId, String status, int userId) {
        // 检查产品是否存在
        Product existingProduct = productMapper.selectByProductId(productId);
        if (existingProduct == null) {
            return Result.error("Product not found");
        }

        // 检查用户是否是发布者
        if (existingProduct.getPublisherId() != userId) {
            return Result.error((short) 7, "The product is not published by this user", null);
        }

        // 检查产品状态
        if ("FROZEN".equals(existingProduct.getStatus()) || "OFF_SHELF".equals(existingProduct.getStatus())) {
            return Result.error((short) 9, "The product is frozen or off the shelf", null);
        }

        // 更新产品状态
        existingProduct.setStatus(status);
        existingProduct.setUpdateTime(LocalDateTime.now());

        int rows = productMapper.update(existingProduct);
        if (rows <= 0) {
            return Result.error((short) 201, "输入信息格式不对");
        }

        return Result.success(existingProduct);
    }

    @Override
    public Result getUserProducts(int userId, int lastProductId, int num) {
        List<Product> products = productMapper.ShowProductsByNum(userId, lastProductId, num);
        int totalNum = productMapper.countByPublisherId(userId);

        return Result.success(new UserProductsResponse(products.size(), totalNum, products));
    }

}
