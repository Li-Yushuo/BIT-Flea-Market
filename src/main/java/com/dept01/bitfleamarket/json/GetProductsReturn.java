package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.product.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class GetProductsReturn {
    private int num;
    private int total_num;
    private List<Product> products;

    public GetProductsReturn(int total_num, List<Product> products) {
        num = products.size();
    }

    public int getTotal_num(){
        return total_num;
    }
}
