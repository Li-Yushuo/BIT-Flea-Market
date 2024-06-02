package com.dept01.bitfleamarket.mapper.user;

import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查找所有User
    @Select("SELECT * FROM user")
    List<User> findAll();

    // 根据userId查找User
    @Select("SELECT * FROM user WHERE user_id = #{userId}")
    User findById(@Param("userId") int userId);

    // 根据bitId查找User
    @Select("SELECT * FROM user WHERE bit_id = #{bitId}")
    User findByBitId(@Param("bitId") String bitId);

    // 新建User
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("INSERT INTO user(bit_id, name, gender, password, contact_info, personal_signature, avatar_url, state, create_time, update_time) " +
            "VALUES(#{bitId}, #{name}, #{gender}, #{password}, #{contactInfo}, #{personalSignature}, #{avatarUrl}, #{state}, #{createTime}, #{updateTime})")
    int insert(User user);

    // 更新User信息
    @Update("UPDATE user SET bit_id=#{bitId}, name=#{name}, gender=#{gender}, password=#{password}, contact_info=#{contactInfo}, personal_signature=#{personalSignature}, avatar_url=#{avatarUrl}, state=#{state}, create_time=#{createTime}, update_time=#{updateTime} WHERE user_id=#{userId}")
    int update(User user);

    // 删除User
    @Delete("DELETE FROM user WHERE user_id = #{userId}")
    int delete(@Param("userId") int userId);
}