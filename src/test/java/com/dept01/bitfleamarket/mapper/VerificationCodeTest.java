package com.dept01.bitfleamarket.mapper;

import com.dept01.bitfleamarket.mapper.user.VerificationCodeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VerificationCodeTest {
    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Test
    void insertCode() {
        String bit_id = "1120211809";
        String code = "123456";
        verificationCodeMapper.insert(bit_id, code);
    }

//    @Test
//    void
//    System.out.println
}

//import java.util.List;
//
//    @Test
//    void showProductsByNumTest() {
//        Integer userId = 1; // replace with actual user id
//        Integer lastProductId = 62; // replace with actual last product id
//        Integer num = 3; // replace with actual number of products to fetch
//
//        List<Product> products = productMapper.showProductsByNum(userId, lastProductId, num);
//
//        for (Product product : products) {
//            System.out.println("ProductId: " + product.getProductId() + ", Name: " + product.getName() + ", Price: " + product.getPrice() + ", Purchase Method: " + product.getPurchaseMethod() + ", Product Category: " + product.getProductCategory() + ", Publisher Id: " + product.getPublisherId() + ", Status: " + product.getStatus() + ", Inventory: " + product.getInventory() + ", Description: " + product.getDescription() + ", Is Anonymous: " + product.getIsAnonymous() + ", Create Time: " + product.getCreateTime() + ", Update Time: " + product.getUpdateTime());
//        }
//    }
//}