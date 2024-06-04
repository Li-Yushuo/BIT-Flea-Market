package com.dept01.bitfleamarket.mapper.user;

import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    // 新建User
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("INSERT INTO user(bit_id, name, gender, password, contact_info, personal_signature, avatar_url, state) " +
            "VALUES(#{bitId}, #{name}, #{gender}, #{password}, #{contactInfo}, #{personalSignature}, #{avatarUrl}, #{state})")
    int insert(User user);
    // 以后创建和更新User或者Product都不需要管createTime和updateTime，因为数据库的触发器会自动填充这两个字段

    // 查找所有User
    @Select("SELECT * FROM user ORDER BY update_time DESC")
    List<User> selectAll();

    // 根据UserId查找用户
    @Select("SELECT * FROM user WHERE user_id = #{userId} ORDER BY update_time DESC")
    User selectByUserId(Integer userId);

    // 根据BitId查找用户
    @Select("SELECT * FROM user WHERE bit_id = #{BitId} ORDER BY update_time DESC")
    User selectByBitId(String BitId);

    // 复合条件查找
    List<User> selectByCondition(Integer userId, String bitId, String name, String gender, String state, LocalDateTime beginCreateTime, LocalDateTime endCreateTime, LocalDateTime beginUpdateTime, LocalDateTime endUpdateTime);
    // 调用这个方法时，你必须提供所有的参数，否则编译器会报错。这意味着你不能省略任何参数。  然而，你可以传入null作为参数值，这在某些情况下可以被视为"不传"参数
    // 应用示例：List<User> users = userMapper.SelectByCondition(43, "2", null, null, null, null, null, null, null);
    // 可以参照Test中的UserMapperTest


    // 更新user，只有传入的非null属性才会被更新
    int update(User user);

    // 根据userId删除User
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    int delete(Integer userId);
}