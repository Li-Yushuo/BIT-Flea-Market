// 修改后的AdminMapper接口
package com.dept01.bitfleamarket.mapper.admin;

import com.dept01.bitfleamarket.pojo.admin.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    // 查找所有Admin
    @Select("SELECT * FROM admin")
    List<Admin> findAll();

    // 根据adminId查找Admin
    @Select("SELECT * FROM admin WHERE admin_id = #{adminId}")
    Admin getAdmin(int adminId);

    @Insert("INSERT INTO admin(password, name, create_time) VALUES(#{password}, #{name}, #{createTime})") // 修改这一行
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    int insertAdmin(Admin admin);

    // 根据adminId删除Admin
    @Delete("DELETE FROM admin WHERE admin_id = #{adminId}")
    int deleteAdmin(int adminId);
}