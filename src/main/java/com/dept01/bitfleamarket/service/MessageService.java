package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.pojo.message.Message;

public interface MessageService {
    boolean readMsg(int messageId);
    UserListReturn getUserListReturn(int fromId);
    MsgListReturn getMsgListReturn(int fromId, String userId);
    boolean saveMsg(Message msg);
}
