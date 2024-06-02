package com.dept01.bitfleamarket.mapper.message;

import com.dept01.bitfleamarket.pojo.message.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    // 插入消息
    @Insert("INSERT INTO message(from_id, to_id, is_anonymous, content, is_read, create_time) VALUES(#{fromId}, #{toId}, #{isAnonymous}, #{content}, #{isRead}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insertMessage(Message message);

    // 更新消息
    // @Update("UPDATE message SET from_id = #{fromId}, to_id = #{toId}, is_anonymous = #{isAnonymous}, content = #{content}, is_read = #{isRead}, create_time = #{createTime} WHERE message_id = #{messageId}")
    // int updateMessage(Message message);

    // 删除消息
    @Delete("DELETE FROM message WHERE message_id = #{messageId}")
    int deleteMessage(int messageId);

    // 根据messageId查询消息
    @Select("SELECT * FROM message WHERE message_id = #{messageId}")
    Message getMessageById(int messageId);

    // 查询所有消息
    @Select("SELECT * FROM message")
    List<Message> findAll();
}