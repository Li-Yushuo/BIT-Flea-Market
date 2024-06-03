package com.dept01.bitfleamarket.Service;

import com.dept01.bitfleamarket.json.GetProductsReturn;
import com.dept01.bitfleamarket.pojo.product.Product;

import java.util.List;

public interface ProductService {
    GetProductsReturn getProducts(int offset, int num, String search_input, String product_categroy, int price_choice);
}
