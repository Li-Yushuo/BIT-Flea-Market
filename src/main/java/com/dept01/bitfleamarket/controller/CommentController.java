package com.dept01.bitfleamarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class CommentController {

    //获取评论列表
    @GetMapping("/comments")
    public String getComments() {
        return "comments";
    }

    //获取评论详情
    @GetMapping("/comments/{comment_id}")
    public String getCommentDetail() {
        return "comment";
    }

    //发布评论
    @PostMapping("/comments")
    public String publishComment() {
        return "publish";
    }

    //删除评论
    @DeleteMapping("/comments/{comment_id}")
    public String deleteComment() {
        return "delete";
    }

}
