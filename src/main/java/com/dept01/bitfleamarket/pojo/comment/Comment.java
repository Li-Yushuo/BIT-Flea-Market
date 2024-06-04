package com.dept01.bitfleamarket.pojo.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer productId;
    // 0: not anonymous, 1: anonymous
    private Integer isAnonymous;
    private String content;
    private LocalDateTime createTime;
}