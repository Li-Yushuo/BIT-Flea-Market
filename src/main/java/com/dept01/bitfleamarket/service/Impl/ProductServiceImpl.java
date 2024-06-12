package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.json.*;
import com.dept01.bitfleamarket.service.ProductService;
import com.dept01.bitfleamarket.mapper.product.ProductImageMapper;
import com.dept01.bitfleamarket.mapper.product.ProductMapper;
import com.dept01.bitfleamarket.mapper.user.UserMapper;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.product.ProductImage;
import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.utils.OBSUploadUtils;
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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductImageMapper productImageMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private OBSUploadUtils obsUploadUtils;

    @Override
    public Result getProducts(int offset, int num, String search_input, String product_category, int price_choice) {
        List<Product> allProducts = productMapper.selectByConditions(search_input, product_category, price_choice);
        // 异常情况
        if (allProducts == null || allProducts.isEmpty() || offset < 0 || offset >= allProducts.size() || num <= 0) {
//            List<Product> tmp = new ArrayList<>();
//            GetProductsReturn getProductsReturn = new GetProductsReturn(0, tmp);
//            return getProductsReturn;
            return Result.error("无满足条件的商品");
        }
        // 正常情况
        else{
            // 计算子列表的结束下标
            int endIndex = Math.min(offset + num, allProducts.size());
            List<Product> tmp = new ArrayList<>(allProducts.subList(offset, endIndex));
            // 获取每个产品的封面图片 URL
            Map<Integer, String> coverImageUrls = tmp.stream()
                    .collect(Collectors.toMap(
                            Product::getProductId,
                            product -> {
                                List<ProductImage> images = productImageMapper.selectByProductId(product.getProductId());
                                return images.isEmpty() ? "{null}" : images.get(0).getImageUrl();
                            }
                    ));

            GetProductsReturn getProductsReturn = GetProductsReturn.fromProducts(allProducts.size(), tmp, coverImageUrls);
            return Result.success(getProductsReturn);
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
        List<ProductImage> productImageList = productImageMapper.selectByProductId(product.getProductId());
        return GetProductByIdReturn.from(product, user, productImageList);
    }

    @Override
    public Result createProduct(CreateProductRequest request, int userId) {
        // LocalDateTime now = LocalDateTime.now();

        // 创建 Product 对象
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setPurchaseMethod(request.getPurchase_method());
        product.setProductCategory(request.getProduct_category());
        product.setPublisherId(userId);
        product.setStatus("on-sale"); // 假设新创建的产品状态为 ACTIVE
        product.setInventory(request.getInventory());
        product.setDescription(request.getDescription());
        product.setIsAnonymous(request.getIs_anonymous());
        // product.setCreateTime(now);
        // product.setUpdateTime(now);

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
            productImage.setImageUrl(imageRequest.getImage_url());
            // productImage.setCreateTime(now);
            productImageMapper.insert(productImage);
        }

        return Result.success(productId);
    }
    @Override
    public Result uploadProductImage(MultipartFile img) throws IOException{
        if (img.isEmpty()) {
            return Result.error("Uploaded file is empty");
        }

        // 生成唯一的文件名
        Map<String, Object> returnMap = obsUploadUtils.uploadProductImage(img);
        String fileName = returnMap.get("url").toString();

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

        // existingProduct.setUpdateTime(LocalDateTime.now());

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
        // existingProduct.setUpdateTime(LocalDateTime.now());

        int rows = productMapper.update(existingProduct);
        if (rows <= 0) {
            return Result.error((short) 201, "输入信息格式不对");
        }

        return Result.success(existingProduct);
    }

    @Override
    public Result getUserProducts(int userId, int lastProductId, int num) {
        List<Product> products = productMapper.showProductsByNum(userId, lastProductId, num);

        List<ProductResponse> productResponses = products.stream().map(product -> {
            List<ProductImage> images = productImageMapper.selectByProductId(product.getProductId());
            String coverImageUrl = images.isEmpty() ? "null" : images.get(0).getImageUrl();

            return new ProductResponse(
                    product.getProductId().toString(),
                    product.getName(),
                    product.getPrice(),
                    product.getPurchaseMethod(),
                    coverImageUrl,
                    product.getProductCategory(),
                    product.getCreateTime(),
                    product.getUpdateTime(),
                    product.getStatus()
            );
        }).collect(Collectors.toList());

        return Result.success(new UserProductsResponse(productResponses.size(), products.size(), productResponses));
    }

}
