package com.dept01.bitfleamarket.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Slf4j
@Component
public class Email {

    @Autowired
    private JavaMailSender mailSender;

    //读取到配置类中的 “发送者”邮箱
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送邮件
     * @param to        接收者邮箱
     * @param subject   邮件的主题
     * @param content   邮件的内容
     */
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(content, true); // 表示可以发送HTML文件
            helper.setSubject(subject);
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            log.error("发送邮件失败：" + e.getMessage());
        }
    }

    public void sendVerificationCode(String bit_id, String code) {
        String toEmailAddress = bit_id + "@bit.edu.cn";
        String subject = "BIT跳蚤市场-邮箱验证码";
        Context context = new Context();
        context.setVariable("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss")));
        context.setVariable("bitid", bit_id);
        context.setVariable("code", code.split(""));
        String content = templateEngine.process("email_code", context);
        this.sendMail(toEmailAddress, subject, content);
    }
}
