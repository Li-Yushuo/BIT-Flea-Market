package com.dept01.bitfleamarket.Service.Impl;

import com.dept01.bitfleamarket.Service.ProductService;
import com.dept01.bitfleamarket.json.GetProductsReturn;
import com.dept01.bitfleamarket.mapper.product.ProductMapper;
import com.dept01.bitfleamarket.pojo.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public GetProductsReturn getProducts(int offset, int num, String search_input, String product_categroy, int price_choice) {
        List<Product> allProducts = productMapper.findAll();
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
}
