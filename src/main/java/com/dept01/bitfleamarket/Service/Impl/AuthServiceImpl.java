package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.json.AuthInfoReturn;
import com.dept01.bitfleamarket.mapper.user.UserMapper;
import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.service.AuthService;
import com.dept01.bitfleamarket.service.EmailService;
import com.dept01.bitfleamarket.utils.JwtUtils;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    //登录服务
    public AuthInfoReturn login(String BitId, String password) {
        // 查询用户是否存在，如果不存在返回null，登陆失败
        User user = userMapper.selectByBitId(BitId);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getUserId());
        String Jwt = JwtUtils.generateJwt(claims);
        // 返回用户登录信息
        AuthInfoReturn authInfoReturn = new AuthInfoReturn();
        authInfoReturn.setToken(Jwt);
        authInfoReturn.setUser_id(user.getUserId());
        authInfoReturn.setAvatar_url(user.getAvatarUrl());
        authInfoReturn.setName(user.getName());
        authInfoReturn.setInfo("登录成功");

        return authInfoReturn;
    }

    //注册服务
    public AuthInfoReturn register(String BitId, String password, String code) {
        // 查询bitid是否已经存在，由于需要邮箱验证，则需要先输入学号接收验证码。此时已经创建了对应学号的用户
//        if (userMapper.selectByBitId(BitId) == null) {
//            return null;
//        }
        // 验证码验证
        if (!verify(code)) {
            return null;
        }
        // 在user表中新增一行
        User user = new User();
        user.setBitId(BitId);
        user.setPassword(password);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setName("shdaiutds");
        user.setGender("unknown");
        user.setAvatarUrl("https://flea-market-obs.obs.ap-southeast-1.myhuaweicloud.com/avatar/kelihead.jpg");
        user.setState("normal");
        if (userMapper.insert(user) != 1) {
            return null;
        }
        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getUserId());
        String Jwt = JwtUtils.generateJwt(claims);
        // 返回用户登录信息
        AuthInfoReturn authInfoReturn = new AuthInfoReturn();
        authInfoReturn.setToken(Jwt);
        authInfoReturn.setUser_id(user.getUserId());
        authInfoReturn.setAvatar_url(user.getAvatarUrl());
        authInfoReturn.setName(user.getName());
        return authInfoReturn;
    }

    //发送邮箱验证码
    public void sendVerificationCode(String BitId) {
        // 查询邮箱是否已经注册
        if (userMapper.selectByBitId(BitId) != null) {
            return;
        }
        //创建对应学号的用户
        User user = new User();
        user.setBitId(BitId);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);

//        User user = userMapper.selectByBitId(BitId);
//        if (user == null) {
//            throw new RuntimeException("邮箱未注册");
//        }
//        // 生成验证码，存入数据库用于验证
//        String code = String.valueOf(new Random().nextInt(900000) + 100000);
//        user.setVerificationCode(code);
//        userMapper.save(user);
//        try {
//            emailService.sendEmail(BitId+"@bit.edu.cn","BIT-Flea-Market Verification Code", code);
//        } catch (MessagingException e) {
//            throw new RuntimeException("邮件发送失败");
//        }
    }

    //验证服务（邮箱验证）
    public boolean verify(String code) {
//        User user = userMapper.findByVerificationCode(code);
//        if (user == null || user.isEnabled()) {
//            throw new RuntimeException("无效的验证请求");
//        }
//        emailService.setVerificationCode(null);
//        user.setEnabled(true);
//        userMapper.save(user);
        return true;

    }
}
