package com.dept01.bitfleamarket.pojo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer messageId;
    private Integer fromId;
    private Integer toId;
    // 0: not anonymous, 1: anonymous
    private Integer isAnonymous;
    private String content;
    // 0: not read, 1: read
    private Integer isRead;
    private LocalDateTime createTime;
}