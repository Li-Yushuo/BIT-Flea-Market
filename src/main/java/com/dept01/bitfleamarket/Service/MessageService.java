package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.pojo.message.Message;

public interface MessageService {
    boolean readMsg(int messageId);
//    UserListReturn getUserListReturn(int last_user_id, int num);
//    MsgListReturn getMsgListReturn(String userId);
    boolean saveMsg(Message msg);
}
