package com.dept01.bitfleamarket.mapper.message;

import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    // 插入消息
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    @Insert("INSERT INTO message(from_id, to_id, is_anonymous, content) VALUES(#{fromId}, #{toId}, #{isAnonymous}, #{content})")
    int insert(Message message);

    // 设置消息为已读
    @Update("UPDATE message SET is_read = 1 WHERE message_id = #{messageId}")
    int setRead(@Param("messageId") int messageId);

    // 删除消息
    @Delete("DELETE FROM message WHERE message_id = #{messageId}")
    int delete(int messageId);

    // 根据messageId查询消息
    @Select("SELECT * FROM message WHERE message_id = #{messageId}")
    Message selectById(int messageId);

    // 根据fromId查询to_id用户列表
//    @Select("SELECT * FROM user WHERE user_id in (SELECT DISTINCT to_id FROM message WHERE from_id = #{fromId} ORDER BY create_time) LIMIT #{num}")
//    List<User> showUsersByNum(int fromId, int last_user_id, int num);
    @Select("SELECT user.* " +
            "FROM user " +
            "JOIN message ON user.user_id = message.from_id " +
            "WHERE user.user_id = #{fromId} AND user.user_id > #{lastUserId} " +
            "GROUP BY user.user_id " +
            "ORDER BY MAX(message.create_time) DESC " +
            "LIMIT #{num}")
    List<User> showUsersByNum(@Param("fromId") int fromId, @Param("lastUserId") int lastUserId, @Param("num") int num);

    //根据fromId和toId查询消息列表
    @Select("SELECT * FROM message WHERE from_id = #{fromId} AND to_id = #{toId} ORDER BY create_time DESC")
    List<Message> selectUsersMsg(int fromId, int toId);

    // 分页查询消息
    @Select("SELECT * FROM message WHERE from_id = #{fromId} AND to_id = #{toId} ORDER BY create_time DESC LIMIT #{num}")
    List<Message> selectMsgsByNum(int fromId, int toId, int num);

    // 查询所有消息
    @Select("SELECT * FROM message ORDER BY create_time DESC")
    List<Message> selectAll();
}