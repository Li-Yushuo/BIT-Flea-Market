package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.product.ProductImage;
import com.dept01.bitfleamarket.pojo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByIdReturn {
    private String name;
    private String price;
    private String purchaseMethod;
    private String productCategory;
    private Publisher publisher;
    private String userId;
    private String avatarUrl;
    private String userName;
    private int inventory;
    private String description;
    private String createdTime;
    private String updatedTime;
    private List<Image> images;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Publisher {
        private String userId;
        private String name;
        private String avatarUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String imageUrl;
    }

    public static GetProductByIdReturn from(Product product, User user, List<ProductImage> productImageList) {
        Publisher publisher = new Publisher(user.getBitId(), user.getName(), user.getAvatarUrl());

        List<Image> images = productImageList.stream()
                .map(image -> new Image(image.getImageUrl()))
                .collect(Collectors.toList());

        return new GetProductByIdReturn(
                product.getName(),
                product.getPrice().toString(),
                product.getPurchaseMethod(),
                product.getProductCategory(),
                publisher,
                user.getBitId(),
                user.getAvatarUrl(),
                user.getName(),
                product.getInventory(),
                product.getDescription(),
                product.getCreateTime().toString(),
                product.getUpdateTime().toString(),
                images
        );
    }
}