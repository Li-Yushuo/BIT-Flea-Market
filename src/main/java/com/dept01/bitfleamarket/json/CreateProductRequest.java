package com.dept01.bitfleamarket.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private BigDecimal price;
    private String purchase_method;
    private String product_category;
    private int inventory;
    private String description;
    private boolean is_anonymous;
    private List<ImageRequest> images;

    public int getIs_anonymous() {return is_anonymous ? 1 : 0;}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageRequest {
        private String image_url;
    }
}
