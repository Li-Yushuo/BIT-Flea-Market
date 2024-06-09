package com.dept01.bitfleamarket.service;

import com.dept01.bitfleamarket.utils.Result;

public interface EmailService {
    // 向bit_id发送邮箱验证码，成功返回true，失败返回false
    public Result sendVerificationCode(String bit_id);
}
