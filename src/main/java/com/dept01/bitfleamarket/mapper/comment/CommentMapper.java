package com.dept01.bitfleamarket.mapper.comment;

import com.dept01.bitfleamarket.pojo.comment.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 插入评论
    @Insert("INSERT INTO comment(user_id, product_id, is_anonymous, content) VALUES(#{userId}, #{productId}, #{isAnonymous}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    int insert(Comment comment);

    // 删除评论
    @Delete("DELETE FROM comment WHERE comment_id = #{commentId}")
    int delete(int commentId);

    // 根据commentId查询评论
    @Select("SELECT * FROM comment WHERE comment_id = #{commentId}")
    Comment selectById(int commentId);

    // 查询所有评论
    @Select("SELECT * FROM comment ORDER BY create_time DESC")
    List<Comment> selectAll();
}