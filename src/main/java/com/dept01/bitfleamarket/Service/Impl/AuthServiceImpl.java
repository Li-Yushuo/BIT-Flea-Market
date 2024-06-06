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


import java.util.HashMap;
import java.util.Map;

import static com.dept01.bitfleamarket.utils.GenRandStrUtils.generateRandomString;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    //登录服务，如果登录成功，返回用户信息，如果登录失败，返回失败信息
    @Override
    public AuthInfoReturn login(String BitId, String password) {
        // 查询用户是否存在
        User user = userMapper.selectByBitId(BitId);
        // 如果不存在该BitId对应的用户，登陆失败
        if (user == null) {
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(101);
            authInfoReturn.setInfo("BitId未注册");
            return authInfoReturn;
        }
        // 用户存在但密码不正确，返回null，登陆失败
        if (!user.getPassword().equals(password)) {
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(101);
            authInfoReturn.setInfo("密码错误");
            return authInfoReturn;
        }
        //用户存在且密码正确，但用户被冻结，登陆失败
        if (user.getState().equals("frozen")) {
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(102);
            authInfoReturn.setInfo("用户被冻结");
            return authInfoReturn;
        }
        //用户存在且密码正确，登陆成功
        // 生成jwt令牌，限时一天
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

    //注册服务，如果注册成功，返回用户信息，如果注册失败，返回失败信息
    @Override
    public AuthInfoReturn register(String BitId, String password, String code) {
        // 查询用户是否存在
        User repetitive_user = userMapper.selectByBitId(BitId);
        // 若该BitId已被注册，抛出错误
        if (repetitive_user != null) {
            // 该BitId已被注册
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(112);
            authInfoReturn.setInfo("BitId已被注册");
            return authInfoReturn;
        }
        // 验证码验证
        if (!verify(BitId,code)) {
            // 验证码错误
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(111);
            authInfoReturn.setInfo("验证码错误");
            return authInfoReturn;

        }
        // 创建用户对象
        User user = new User();
        user.setBitId(BitId);
        user.setPassword(password);
        user.setName(generateRandomString(10));
        user.setGender("unknown");
        user.setAvatarUrl("https://flea-market-obs.obs.ap-southeast-1.myhuaweicloud.com/avatar/kelihead.jpg"); // 默认头像
        user.setState("normal");
        user.setContactInfo(BitId+"@bit.edu.cn");
        user.setPersonalSignature("这个人很懒，什么都没留下~");
        // 在user表中新增用户对象
        if (userMapper.insert(user) != 1) {
            // 注册失败
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(131);
            authInfoReturn.setInfo("注册失败，请联系管理员");
            return authInfoReturn;
        }
        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getUserId());
        String Jwt = JwtUtils.generateJwt(claims);
        // 返回用户注册信息（注册成功时自动登录）
        AuthInfoReturn authInfoReturn = new AuthInfoReturn();
        authInfoReturn.setToken(Jwt);
        authInfoReturn.setUser_id(user.getUserId());
        authInfoReturn.setAvatar_url(user.getAvatarUrl());
        authInfoReturn.setName(user.getName());
        authInfoReturn.setInfo("注册成功");
        return authInfoReturn;
    }

    //修改密码服务，如果修改成功，返回用户信息，如果修改失败，返回失败信息
    @Override
    public AuthInfoReturn modifyPassword(String BitId, String password, String code) {
        // 查询用户是否存在
        User user = userMapper.selectByBitId(BitId);
        // 若该BitId未注册，抛出错误
        if (user == null) {
            // 该BitId未注册
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(101);
            authInfoReturn.setInfo("BitId未注册");
            return authInfoReturn;
        }
        // 验证码验证
        if (!verify(BitId,code)) {
            // 验证码错误
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(111);
            authInfoReturn.setInfo("验证码错误");
            return authInfoReturn;
        }
        // 修改密码
        user.setPassword(password);
        // 在user表中更新用户对象
        if (userMapper.update(user) != 1) {
            // 修改失败
            AuthInfoReturn authInfoReturn = new AuthInfoReturn();
            authInfoReturn.setUser_id(131);
            authInfoReturn.setInfo("修改失败，请联系管理员");
            return authInfoReturn;
        }
        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserId", user.getUserId());
        String Jwt = JwtUtils.generateJwt(claims);
        // 返回用户信息
        AuthInfoReturn authInfoReturn = new AuthInfoReturn();
        authInfoReturn.setToken(Jwt);
        authInfoReturn.setUser_id(user.getUserId());
        authInfoReturn.setAvatar_url(user.getAvatarUrl());
        authInfoReturn.setName(user.getName());
        authInfoReturn.setInfo("修改成功");
        return authInfoReturn;
    }

    //发送邮箱验证码
    public void sendVerificationCode(String BitId) {
        // 查询邮箱是否已经注册
        User repetitive_user = userMapper.selectByBitId(BitId);
        // 若该邮箱已被注册，抛出错误
        if (repetitive_user != null) {
            // 抛出错误，该邮箱已被注册
            // 请实现

        }
        //创建对应学号的用户
        User user = new User();
        user.setBitId(BitId);
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
    public boolean verify(String BitId,String code) {
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
