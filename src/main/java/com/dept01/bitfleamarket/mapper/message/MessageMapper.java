package com.dept01.bitfleamarket.mapper.message;

import com.dept01.bitfleamarket.pojo.message.Message;
import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    // 插入消息
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    @Insert("INSERT INTO message(from_id, to_id, is_anonymous, content, is_read, create_time) VALUES(#{fromId}, #{toId}, #{isAnonymous}, #{content}, #{isRead}, #{createTime})")
    int insertMessage(Message message);

    // 更新消息
    // @Update("UPDATE message SET from_id = #{fromId}, to_id = #{toId}, is_anonymous = #{isAnonymous}, content = #{content}, is_read = #{isRead}, create_time = #{createTime} WHERE message_id = #{messageId}")
    // int updateMessage(Message message);

    // 根据messageId查找User
    @Select("SELECT * FROM user WHERE message_id = #{messageId}")
    User selectById(@Param("messageId") int messageId);

    // 设置消息为已读
    @Update("UPDATE message SET is_read = 1 WHERE message_id = #{messageId}")
    int setRead(@Param("messageId") int messageId);

    // 删除消息
    @Delete("DELETE FROM message WHERE message_id = #{messageId}")
    int deleteMessage(int messageId);

    // 根据messageId查询消息
    @Select("SELECT * FROM message WHERE message_id = #{messageId}")
    Message getMessageById(int messageId);

    // 查询所有消息
    @Select("SELECT * FROM message")
    List<Message> SelectAll();

    // 根据fromId查询所有行

    // 根据fromId查询行数


}