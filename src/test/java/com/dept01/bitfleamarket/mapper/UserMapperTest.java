package com.dept01.bitfleamarket.mapper;

import com.dept01.bitfleamarket.mapper.user.UserMapper;
import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insertTest() {
        User newUser = new User();
        newUser.setBitId("testBitId");
        newUser.setName("testName");
        newUser.setGender("male");
        newUser.setPassword("testPassword");
        newUser.setContactInfo("testContactInfo");
        newUser.setPersonalSignature("testPersonalSignature");
        newUser.setAvatarUrl("");
        newUser.setState("normal");
        newUser.setCreateTime(LocalDateTime.now());
        newUser.setUpdateTime(LocalDateTime.now());

        try {
            int rowsAffected = userMapper.insert(newUser);
            if (rowsAffected == 0) {
                // 插入操作没有影响任何行，可能是因为user对象的属性没有正确设置
                System.out.println("Insert operation failed: no rows affected");
            } else {
                System.out.println(newUser.getUserId());
            }
            System.out.println(rowsAffected);
        } catch (PersistenceException e) {
            // 插入操作失败，可能是因为数据库错误
            System.out.println("Insert operation failed: " + e.getMessage());
        } catch (DataIntegrityViolationException e) {
            // 插入操作失败，可能是因为违反了数据库的数据完整性规则
            System.out.println("Insert operation failed due to data integrity violation: " + e.getMessage());
        }
    }





    @Test
    void selectTest() {

//        List<User> user_list = userMapper.selectAll();
//        for (User user : user_list) {
//            System.out.println("UserId: " + user.getUserId() + ", BitId: " + user.getBitId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Password: " + user.getPassword() + ", Contact Info: " + user.getContactInfo() + ", Personal Signature: " + user.getPersonalSignature() + ", Avatar Url: " + user.getAvatarUrl() + ", State: " + user.getState() + ", Create Time: " + user.getCreateTime() + ", Update Time: " + user.getUpdateTime());
//        }

        // 选择bitId为"72"的用户
        List<User> bitid_72 = userMapper.selectByCondition(null, "72", null, null, null, null, null, null, null);
        for (User user : bitid_72) {
            System.out.println("UserId: " + user.getUserId() + ", BitId: " + user.getBitId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Password: " + user.getPassword() + ", Contact Info: " + user.getContactInfo() + ", Personal Signature: " + user.getPersonalSignature() + ", Avatar Url: " + user.getAvatarUrl() + ", State: " + user.getState() + ", Create Time: " + user.getCreateTime() + ", Update Time: " + user.getUpdateTime());
        }

        // 选择20240604当天内创建的用户
        List<User> Time_between_20240604000000_to_20240604235959 = userMapper.selectByCondition(null, null, null, null, null, LocalDateTime.of(2024, 6, 4, 0, 0, 0), LocalDateTime.of(2024, 6, 4, 23, 59, 59), null, null);
        for (User user : Time_between_20240604000000_to_20240604235959) {
            System.out.println("UserId: " + user.getUserId() + ", BitId: " + user.getBitId() + ", Name: " + user.getName() + ", Gender: " + user.getGender() + ", Password: " + user.getPassword() + ", Contact Info: " + user.getContactInfo() + ", Personal Signature: " + user.getPersonalSignature() + ", Avatar Url: " + user.getAvatarUrl() + ", State: " + user.getState() + ", Create Time: " + user.getCreateTime() + ", Update Time: " + user.getUpdateTime());
        }

    }
    @Test
    void updateTest() {
        User newUser = new User();
        newUser.setUserId(1);
        newUser.setBitId("1120218765");
        newUser.setName("testName_updated");
        if (userMapper.update(newUser) == 1) {
            System.out.println("Update operation succeeded");
        } else {
            System.out.println("Update operation failed");
        }


    }
}