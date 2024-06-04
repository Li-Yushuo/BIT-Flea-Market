package com.dept01.bitfleamarket.json;

import com.dept01.bitfleamarket.pojo.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgListReturn {
    private int num;
    private List<Message> messages;
}
