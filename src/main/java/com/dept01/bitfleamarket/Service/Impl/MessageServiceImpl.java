package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.mapper.message.MessageMapper;
import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.pojo.user.UserBriefInfo;
import com.dept01.bitfleamarket.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.dept01.bitfleamarket.pojo.user.UserBriefInfo.trimUserListInfo;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean saveMsg(Message msg){
        int code = messageMapper.insert(msg);
        return code == 1;
    }

    public boolean readMsg(int messageId) {
        return messageMapper.setRead(messageId) == 1;
    }

    public UserListReturn getUserListReturn(int fromId,int last_user_id, int num) {
        List<User> userList= messageMapper.showUsersByNum(fromId, last_user_id, num);
        List<UserBriefInfo> userBriefList = new java.util.ArrayList<>(List.of());
        for (User user : userList) {
            UserBriefInfo temp = new UserBriefInfo(user);
            userBriefList.add(trimUserListInfo(temp));
        }
        return new UserListReturn(userList.size(), userBriefList);
    }

    public MsgListReturn getMsgListReturn(int fromId, String userId) {
        List<Message>  MsgList = messageMapper.selectUsersMsg(fromId, Integer.parseInt(userId));
        return new MsgListReturn(MsgList.size(), MsgList);
    }
}
