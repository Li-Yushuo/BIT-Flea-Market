package com.dept01.bitfleamarket.json;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.dept01.bitfleamarket.pojo.message.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgRequest {
    private Integer from_id;
    private Integer to_id;
    private String content;

    public static Message toMessage(MsgRequest msgRequest) {
        Message message = new Message();
        message.setFromId(msgRequest.getFrom_id());
        message.setToId(msgRequest.getTo_id());
        message.setContent(msgRequest.getContent());
        message.setIsAnonymous(0);
        message.setIsRead(0);
        return message;
    }
}
