package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.json.MsgRequest;
import com.dept01.bitfleamarket.service.MessageService;
import com.dept01.bitfleamarket.json.MsgListReturn;
import com.dept01.bitfleamarket.json.UserListReturn;
import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.utils.JwtUtils;
import com.dept01.bitfleamarket.utils.Result;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dept01.bitfleamarket.json.MsgRequest.toMessage;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    //发送消息保存到数据库
    @PostMapping("/messages")
    public Result sendMsg(@RequestBody MsgRequest msg, HttpServletRequest httpRequest) {
        //获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        int fromId = jwt_claims.get("UserId", Integer.class);

        msg.setFrom_id(fromId);
        if(messageService.saveMsg(toMessage(msg))){
            return Result.success();
        }
        else{
            return Result.error("发送失败");
        }
    }

    //查看消息标记已读
    @PostMapping("/messages/{message_id}/read")
    public Result readMsg(@PathVariable("message_id") int messageId){
        if(messageService.readMsg(messageId)){
            return Result.success();
        }
        else{
            return Result.error("标记已读失败");
        }
    }

    //获取用户聊天的用户列表
    @GetMapping("/messages/users")
    public Result getMsgUserList(HttpServletRequest httpRequest) {
        //获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        int fromId = jwt_claims.get("UserId", Integer.class);

        UserListReturn userListReturn = messageService.getUserListReturn(fromId);
        if (userListReturn == null || userListReturn.getUsers().isEmpty()){
            return Result.error("获取用户列表失败");
        }
        return Result.success(userListReturn);
    }

    //获取和某一用户用户聊天记录
    @GetMapping("/messages/users/{user_id}")
    public Result getUserMsgList(@PathVariable("user_id") String userId, HttpServletRequest httpRequest){
        //获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = httpRequest.getHeader("Authorization");
        jwt = jwt.split(" ")[1];
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        int fromId = jwt_claims.get("UserId", Integer.class);

        MsgListReturn msgListReturn = messageService.getMsgListReturn(fromId, userId);
        if (msgListReturn == null || msgListReturn.getMessages().isEmpty()){
            return Result.error("获取聊天记录失败");
        }
        return Result.success(msgListReturn);
    }
}
