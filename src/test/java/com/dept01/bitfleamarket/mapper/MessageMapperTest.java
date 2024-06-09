package com.dept01.bitfleamarket.mapper;

import com.dept01.bitfleamarket.mapper.message.MessageMapper;
import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MessageMapperTest {

//    @Autowired
//    private MessageMapper messageMapper;
//
//    @Test
//    void insertTest() {
//        Message message = new Message();
//        // set properties for message
//        message.setFromId(1); // replace with actual from_id
//        message.setToId(18); // replace with actual to_id
//        message.setIsAnonymous(0); // replace with actual is_anonymous value
//        message.setContent("Test content"); // replace with actual content
//        int result = messageMapper.insert(message);
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void setReadTest() {
//        int messageId = 1; // replace with actual message id
//        int result = messageMapper.setRead(messageId);
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void deleteMessageTest() {
//        int messageId = 1; // replace with actual message id
//        int result = messageMapper.delete(messageId);
//        assertTrue(result > 0);
//    }
//
//    @Test
//    void selectByIdTest() {
//        int messageId = 1; // replace with actual message id
//        Message message = messageMapper.selectById(messageId);
//        assertNotNull(message);
//    }
//
//    @Test
//    void selectUserListTest() {
//        int fromId = 25; // replace with actual from id
//        List<User> to_users = messageMapper.showUsersByNum(fromId, 0, 1000);
//        for (User to_user : to_users) {
//            System.out.println("toUserId: " + to_user.getUserId());
//        }
//        assertFalse(to_users.isEmpty());
//    }
//
//    @Test
//    void selectUsersMsgTest() {
//        int fromId = 1; // replace with actual from id
//        int toId = 1; // replace with actual to id
//        List<Message> messages = messageMapper.selectUsersMsg(fromId, toId);
//        assertFalse(messages.isEmpty());
//    }
//
//    @Test
//    void selectAllTest() {
//        List<Message> messages = messageMapper.selectAll();
//        assertFalse(messages.isEmpty());
//    }
}