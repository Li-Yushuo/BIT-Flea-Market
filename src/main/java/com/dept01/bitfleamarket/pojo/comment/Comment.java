package com.dept01.bitfleamarket.pojo.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int commentId;
    private int userId;
    private int productId;
    private boolean isAnonymous;
    private String content;
    private LocalDateTime createTime;
}