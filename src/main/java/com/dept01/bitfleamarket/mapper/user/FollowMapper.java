package com.dept01.bitfleamarket.mapper.user;

import com.dept01.bitfleamarket.pojo.user.Follow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FollowMapper {
    // 插入关注
    @Insert("INSERT INTO follow(follower_id, followed_id, follow_time) VALUES(#{followerId}, #{followedId}, #{followTime})")
    int insertFollow(Follow follow);

    // 删除关注
    @Delete("DELETE FROM follow WHERE follower_id = #{followerId} AND followed_id = #{followedId}")
    int deleteFollow(@Param("followerId") int followerId, @Param("followedId") int followedId);

    // 根据followerId查询关注
    @Select("SELECT * FROM follow WHERE follower_id = #{followerId}")
    List<Follow> getFollowByFollowerId(int followerId);
}