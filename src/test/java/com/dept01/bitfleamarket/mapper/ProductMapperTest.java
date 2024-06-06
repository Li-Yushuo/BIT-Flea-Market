package com.dept01.bitfleamarket.mapper;

import com.dept01.bitfleamarket.mapper.product.ProductMapper;
import com.dept01.bitfleamarket.pojo.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void showProductsByNumTest() {
        Integer userId = 1; // replace with actual user id
        Integer lastProductId = 62; // replace with actual last product id
        Integer num = 3; // replace with actual number of products to fetch

        List<Product> products = productMapper.showProductsByNum(userId, lastProductId, num);

        for (Product product : products) {
            System.out.println("ProductId: " + product.getProductId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Purchase Method: " + product.getPurchaseMethod() + ", Product Category: " + product.getProductCategory() + ", Publisher Id: " + product.getPublisherId() + ", Status: " + product.getStatus() + ", Inventory: " + product.getInventory() + ", Description: " + product.getDescription() + ", Is Anonymous: " + product.getIsAnonymous() + ", Create Time: " + product.getCreateTime() + ", Update Time: " + product.getUpdateTime());
        }
    }
}