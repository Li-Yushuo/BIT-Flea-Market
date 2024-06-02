package com.dept01.bitfleamarket.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int messageId;
    private int fromId;
    private int toId;
    private boolean isAnonymous;
    private String content;
    private boolean isRead;
    private LocalDateTime createTime;
}