package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.mapper.message.MessageMapper;
import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    public boolean saveMsg(Message msg){
        int code = messageMapper.insertMessage(msg);
        return code != 1;
    }

    public boolean readMsg(int messageId) {
        return messageMapper.setRead(messageId) == 1;
    }

//    public UserListReturn getUserListReturn(int last_user_id, int num) {
//        return messageMapper.selectUserList(last_user_id, num);
//    }
//
//    public MsgListReturn getMsgListReturn(String userId) {
//        List<Message>  MsgList = messageMapper.selectbyId(userId);
//        return new MsgListReturn(MsgList.size(), MsgList);
//    }
}
