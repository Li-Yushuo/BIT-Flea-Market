package com.dept01.bitfleamarket.mapper.comment;

import com.dept01.bitfleamarket.pojo.comment.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 插入评论
    @Insert("INSERT INTO comment(user_id, product_id, is_anonymous, content, create_time) VALUES(#{userId}, #{productId}, #{isAnonymous}, #{content}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    int insertComment(Comment comment);

    // 更新评论
    // @Update("UPDATE comment SET user_id = #{userId}, product_id = #{productId}, is_anonymous = #{isAnonymous}, content = #{content}, create_time = #{createTime} WHERE comment_id = #{commentId}")
    // int updateComment(Comment comment);

    // 删除评论
    @Delete("DELETE FROM comment WHERE comment_id = #{commentId}")
    int deleteComment(int commentId);

    // 根据commentId查询评论
    @Select("SELECT * FROM comment WHERE comment_id = #{commentId}")
    Comment getCommentById(int commentId);

    // 查询所有评论
    @Select("SELECT * FROM comment")
    List<Comment> SelectAll();
}