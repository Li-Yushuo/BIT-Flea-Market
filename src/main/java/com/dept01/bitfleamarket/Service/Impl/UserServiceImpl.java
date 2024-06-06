package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.pojo.user.UserBriefInfo;
import com.dept01.bitfleamarket.service.UserService;
import com.dept01.bitfleamarket.mapper.user.UserMapper;
import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.utils.OBSUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

import static com.dept01.bitfleamarket.pojo.user.UserBriefInfo.trimUserInfo;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OBSUploadUtils obsUploadUtils;

    //查询用户信息
    @Override
    public UserBriefInfo getUserInfo(String userId) {
        return trimUserInfo(new UserBriefInfo(userMapper.selectByUserId(Integer.parseInt(userId))));
    }

    //修改用户信息
    @Override
    public void modifyUserInfo(String userId, UserBriefInfo user_in) {
        User user = new User(user_in);
        user.setUserId(Integer.parseInt(userId));
        userMapper.update(user);
    }

    //上传头像
    @Override
    public String uploadAvatar(String userId, MultipartFile avatar) {
        String imageUrl = convertAndSaveImage(avatar);//上传文件工具类实现，此处调用
        User user = userMapper.selectByUserId(Integer.parseInt(userId));
        user.setAvatarUrl(imageUrl);
        userMapper.update(user);
        return imageUrl;
    }

    //上传文件的实现
    private String convertAndSaveImage(MultipartFile image)  {

        log.info("文件上传, 文件名: {}", image.getOriginalFilename());
        try{
        Map<String, Object> returnMap = obsUploadUtils.uploadAvatar(image);
            log.info("文件上传完成,文件访问的url: {}", returnMap.get("url"));
            return returnMap.get("url").toString();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}