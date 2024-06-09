package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.mapper.product.ProductImageMapper;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.product.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsReturn {
    private int num;
    private int total_num;
    private List<ProductDTO> products;

    public GetProductsReturn(int totalNum, List<ProductDTO> products) {
        this.num = products.size();
        this.total_num = totalNum;
        this.products = products;
    }

    public static GetProductsReturn fromProducts(int size, List<Product> tmp, Map<Integer, String> coverImageUrls) {
        List<ProductDTO> productDTOList = tmp.stream()
                .map(product -> ProductDTO.fromProduct(product, coverImageUrls.get(product.getProductId())))
                .collect(Collectors.toList());
        return new GetProductsReturn(size, productDTOList);
    }

    @Data
    public static class ProductDTO {
        private int product_id;
        private String name;
        private BigDecimal price;
        private String cover_image_url;
        private LocalDateTime updated_time;

        public static ProductDTO fromProduct(Product product, String coverImageUrl) {
            ProductDTO dto = new ProductDTO();
            dto.setProduct_id(product.getProductId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setUpdated_time(product.getUpdateTime());
            dto.setCover_image_url(coverImageUrl);
            return dto;
        }

    // 静态方法，用于转换Product列表到GetProductsReturn对象
//    public static GetProductsReturn fromProducts(int totalNum, List<Product> products, Map<Integer, String> coverImageUrls) {
//
//    }
    }
}
