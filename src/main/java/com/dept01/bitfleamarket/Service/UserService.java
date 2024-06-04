package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.pojo.user.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getUserInfo(String userId);
    void modifyUserInfo(String userId, User user);
    String uploadAvatar(String userId, MultipartFile avatar);

}