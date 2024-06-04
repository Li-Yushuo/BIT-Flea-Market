package com.dept01.bitfleamarket.json;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private BigDecimal price;
    private String purchaseMethod;
    private String productCategory;
    private Integer inventory;
    private String description;
    private Boolean isAnonymous;
    private List<ImageRequest> images;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageRequest {
        private String imageUrl;
    }
}
