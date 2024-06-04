package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.service.MessageService;
import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    //发送消息保存到数据库
    @PostMapping("/messages")
    public Result sendMsg(@RequestBody Message msg) {
        messageService.saveMsg(msg);
        return Result.success();
    }

    //查看消息标记已读
    @PutMapping("/messages/{message_id}/read")
    public Result readMsg(@PathVariable("message_id") int messageId){
        messageService.readMsg(messageId);
        return Result.success();
    }

//    //获取用户聊天的用户列表
//    @GetMapping("/messages/users")
//    public Result getMsgUserList(int last_user_id, int num) {
//        UserListReturn userListReturn = messageService.getUserListReturn(last_user_id, num);
//        return Result.success(userListReturn);
//    }
//
//    //获取和某一用户用户聊天记录
//    @GetMapping("/messages/users/{user_id}")
//    public Result getUserMsgList(@PathVariable("user_id") String userId){
//        MsgListReturn msgListReturn = messageService.getMsgListReturn(userId);
//        return Result.success(msgListReturn);
//    }
}
