package com.dept01.bitfleamarket.mapper.user;

import com.dept01.bitfleamarket.pojo.user.Collect;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {
    // 插入收藏
    @Insert("INSERT INTO collect(user_id, product_id, collect_time) VALUES(#{userId}, #{productId}, #{collectTime})")
    int insertCollect(Collect collect);

    // 删除收藏
    @Delete("DELETE FROM collect WHERE user_id = #{userId} AND product_id = #{productId}")
    int deleteCollect(@Param("userId") int userId, @Param("productId") int productId);

    // 根据userId查询收藏
    @Select("SELECT * FROM collect WHERE user_id = #{userId}")
    List<Collect> getCollectByUserId(int userId);
}