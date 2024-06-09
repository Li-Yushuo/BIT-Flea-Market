package com.dept01.bitfleamarket.mapper.user;

import com.dept01.bitfleamarket.pojo.product.Product;
import com.dept01.bitfleamarket.pojo.user.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface VerificationCodeMapper {

//    CREATE TABLE verification_code (
//            code_id INTEGER NOT NULL AUTO_INCREMENT,
//            bit_id CHAR(10) NOT NULL,
//    code VARCHAR(6) NOT NULL,
//    PRIMARY KEY (code_id)
//);

    // 新建Code
    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO verification_code(bit_id, code) " +
            "VALUES(#{bitId}, #{code})")
    int insert(String bitId, String code);
    // 以后创建和更新User或者Product都不需要管createTime和updateTime，因为数据库的触发器会自动填充这两个字段

    // 根据bit_id查询code
    @Select("SELECT code FROM verification_code WHERE bit_id = #{bitId} ORDER BY create_time DESC LIMIT 1")
    String selectByBitId(String bitId);

    // 根据bit_id删除code
//    @Delete("DELETE FROM user WHERE bit_id = #{bitId}")
//    int delete(String bitId);
}
