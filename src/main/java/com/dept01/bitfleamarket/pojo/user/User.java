package com.dept01.bitfleamarket.pojo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String bitId;
    private String name;
    private String gender; // 在数据库中为enum类型
    private String password;
    private String contactInfo;
    private String personalSignature;
    private String avatarUrl;
    private String state; // 在数据库中为enum类型
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public User(UserBriefInfo userBriefInfo) {
        if (userBriefInfo != null) {
            if (userBriefInfo.getUser_id() != null) {
                this.userId = userBriefInfo.getUser_id();
            }
            if (userBriefInfo.getBit_id() != null) {
                this.bitId = userBriefInfo.getBit_id();
            }
            if (userBriefInfo.getName() != null) {
                this.name = userBriefInfo.getName();
            }
            if (userBriefInfo.getAvatar_url() != null) {
                this.avatarUrl = userBriefInfo.getAvatar_url();
            }
            if (userBriefInfo.getGender() != null) {
                this.gender = userBriefInfo.getGender();
            }
            if (userBriefInfo.getContact_info() != null) {
                this.contactInfo = userBriefInfo.getContact_info();
            }
            if (userBriefInfo.getPersonal_signature() != null) {
                this.personalSignature = userBriefInfo.getPersonal_signature();
            }
        }
    }
}