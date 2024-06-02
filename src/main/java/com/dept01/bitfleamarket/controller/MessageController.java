package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    //发送消息保存到数据库
    @PostMapping("/messages")
    public String sendMsg() {
        return "send";
    }

    //查看消息标记已读
    @PutMapping("/messages/{message_id}/read")
    public String readMsg() {
        return "read";
    }

    //获取用户聊天的用户列表
    @GetMapping("/messages/users")
    public String getMsgUserList() {
        return "users";
    }

    //获取和某一用户用户聊天记录
    @GetMapping("/messages/users/{user_id}")
    public String getUserMsgList() {
        return "user";
    }
}
