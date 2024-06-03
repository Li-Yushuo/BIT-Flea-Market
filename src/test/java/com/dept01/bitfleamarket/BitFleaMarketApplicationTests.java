//package com.dept01.bitfleamarket;
//
//import com.dept01.bitfleamarket.mapper.user.UserMapper;
//import com.dept01.bitfleamarket.pojo.user.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class BitFleaMarketApplicationTests {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    void testInsert() {
//        User newUser = new User();
//        newUser.setBitId("testBitId");
//        newUser.setName("testName");
//        newUser.setGender("male");
//        newUser.setPassword("testPassword");
//        newUser.setContactInfo("testContactInfo");
//        newUser.setPersonalSignature("testPersonalSignature");
//        newUser.setAvatarUrl("");
//        newUser.setState("normal");
//        newUser.setCreateTime(LocalDateTime.now());
//        newUser.setUpdateTime(LocalDateTime.now());
//
//        int result = userMapper.insert(newUser);
//        System.out.println(newUser.getUserId());
//
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void testFindAll() {
//        List<User> users = userMapper.findAll();
//        System.out.println(users);
//        assertNotNull(users);
//    }
//
//}