package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.product.ProductImage;
import com.dept01.bitfleamarket.pojo.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByIdReturn {
    private String name;
    private String price;
    private String purchase_method;
    private String product_categroy;
    private Publisher publisher;
//    private int userId;
//    private String avatarUrl;
//    private String userName;
    private int inventroy;
    private String description;
    private String created_time;
    private String updated_time;
    private List<Image> images;
    private List<String> labels;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Publisher {
        private int user_id;
        private String name;
        private String avatar_url;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        private String imageUrl;
    }

    public static GetProductByIdReturn from(Product product, User user, List<ProductImage> productImageList) {

        Publisher publisher = null;
        if (product.getIsAnonymous() == 1)
            publisher = new Publisher(-1, "匿名用户", "https://flea-market-obs.obs.ap-southeast-1.myhuaweicloud.com/avatar/anonymous_avatar.jpeg");
        else
            publisher = new Publisher(user.getUserId(), user.getName(), user.getAvatarUrl());

        List<Image> images = productImageList.stream()
                .map(image -> new Image(image.getImageUrl()))
                .collect(Collectors.toList());

        return new GetProductByIdReturn(
                product.getName(),
                product.getPrice().toString(),
                product.getPurchaseMethod(),
                product.getProductCategory(),
                publisher,
//                user.getUserId(),
//                user.getAvatarUrl(),
//                user.getName(),
                product.getInventory(),
                product.getDescription(),
                product.getCreateTime().toString(),
                product.getUpdateTime().toString(),
                images,
                new ArrayList<>()
        );
    }
}