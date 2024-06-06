package com.dept01.bitfleamarket.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBriefInfo {
    private Integer user_id;
    private String bit_id;
    private String name;
    private String avatar_url;
    private String gender;
    private String contact_info;
    private String personal_signature;
    private LocalDateTime create_time;

    public UserBriefInfo(User user) {
        this.user_id = user.getUserId();
        this.bit_id = user.getBitId();
        this.name = user.getName();
        this.avatar_url = user.getAvatarUrl();
        this.gender = user.getGender();
        this.contact_info = user.getContactInfo();
        this.personal_signature = user.getPersonalSignature();
        this.create_time = user.getCreateTime();
    }

    public static UserBriefInfo trimUserInfo(UserBriefInfo userBriefInfo) {
        userBriefInfo.setUser_id(null);
        return userBriefInfo;
    }

    public static UserBriefInfo trimUserListInfo(UserBriefInfo userBriefInfo) {
        userBriefInfo.setName(null);
        userBriefInfo.setGender(null);
        userBriefInfo.setContact_info(null);
        userBriefInfo.setPersonal_signature(null);
        userBriefInfo.setCreate_time(null);
        return userBriefInfo;
    }

}
