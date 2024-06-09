package com.dept01.bitfleamarket.service.Impl;

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

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    Email email;

    @Override
    public Result sendVerificationCode(String bit_id) {
        email.sendVerificationCode(bit_id, "123455");
        return Result.success();
    }
}