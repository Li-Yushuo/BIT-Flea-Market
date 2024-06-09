package com.dept01.bitfleamarket.service.Impl;

import com.dept01.bitfleamarket.mapper.user.VerificationCodeMapper;
import com.dept01.bitfleamarket.service.EmailService;
import com.dept01.bitfleamarket.utils.Email;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Email email;

    @Autowired
    private VerificationCodeMapper verifyMapper;

    @Override
    public Result sendVerificationCode(String bit_id) {
        // 生成验证码，存入数据库用于验证
        String code = String.valueOf(new Random().nextInt(900000) + 100000);
        email.sendVerificationCode(bit_id, code);
        //存入数据库
        verifyMapper.insert(bit_id, code);
        return Result.success();
    }
}