package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductsResponse {
        private int num;
        private int totalNum;
        private List<ProductResponse> products;
}
