package com.dept01.bitfleamarket.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBriefInfo {
    private int user_id;
    private String name;
    private String avatar_url;

    public UserBriefInfo(User user) {
        this.user_id = user.getUserId();
        this.name = user.getName();
        this.avatar_url = user.getAvatarUrl();
    }
}
