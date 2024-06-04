package com.dept01.bitfleamarket.service.Impl;

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

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OBSUploadUtils obsUploadUtils;

    @Override
    public User getUserInfo(String userId) {
        return userMapper.selectByUserId(Integer.parseInt(userId));
    }

    @Override
    public void modifyUserInfo(String userId, User user) {
        user.setUserId(Integer.parseInt(userId));
        userMapper.update(user);
    }

    @Override
    public String uploadAvatar(String userId, MultipartFile avatar) {
        // 这里需要实现将MultipartFile保存为图片的逻辑，并将图片的URL保存到用户信息中
        // 假设你已经有了一个将MultipartFile转换为URL的方法，名为convertAndSaveImage
        String imageUrl = convertAndSaveImage(avatar);//上传文件工具类实现，此处调用
        User user = userMapper.selectByUserId(Integer.parseInt(userId));
        user.setAvatarUrl(imageUrl);
        userMapper.update(user);
        return imageUrl;
    }

    private String convertAndSaveImage(MultipartFile image)  {
        // 这里实现将MultipartFile转换为URL的逻辑
        // 返回图片的URL
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