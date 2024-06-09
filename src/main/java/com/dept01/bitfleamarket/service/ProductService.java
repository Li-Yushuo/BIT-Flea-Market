package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.json.CreateProductRequest;
import com.dept01.bitfleamarket.json.GetProductByIdReturn;
import com.dept01.bitfleamarket.json.GetProductsReturn;
import com.dept01.bitfleamarket.json.UpdateProductRequest;
import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Result getProducts(int offset, int num, String search_input, String product_category, int price_choice);
    GetProductByIdReturn getProductById(String product_id);
    Result createProduct(CreateProductRequest request, int userId);
    Result uploadProductImage(MultipartFile img) throws IOException;
    Result updateProduct(int productId, UpdateProductRequest request, int userId);
    Result updateProductStatus(int productId, String status, int userId);
    Result getUserProducts(int userId, int lastProductId, int num);
}


