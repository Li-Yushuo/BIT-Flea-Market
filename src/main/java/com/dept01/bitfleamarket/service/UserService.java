package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.pojo.user.UserBriefInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserBriefInfo getUserInfo(String userId);
    void modifyUserInfo(String userId, UserBriefInfo user);
    String uploadAvatar(String userId, MultipartFile avatar);

}