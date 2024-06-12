package com.dept01.bitfleamarket.interceptor;


import com.dept01.bitfleamarket.utils.Result;
import com.dept01.bitfleamarket.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override //目标资源方法运行前运行, 返回true: 放行, 放回false, 不放行
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}", url);

        //2.判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (url.contains("login") || url.contains("register") || url.contains("verify") || url.contains("modify_password")) {
            log.info("登录操作, 放行...");
            return true;
        }

        //如果请求是get请求并且url中包含products，放行
        if ("GET".equalsIgnoreCase(req.getMethod()) && url.contains("products")) {
            log.info("GET请求, 放行...");
            return true;
        }

        //3.获取请求头中的令牌（token）。
        String jwt = req.getHeader("Authorization");
        jwt = jwt.split(" ")[1];

        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头token为空,返回未登录的信息");
            Result error = Result.error("NOT_LOGIN_NO_TOKEN");
            resp.getWriter().write(objectMapper.writeValueAsString(error));
            return false;
        }

        //5.解析token，如果解析失败，返回错误结果（未登录）。
        Claims claims;
        try {
            claims = JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//jwt解析失败
            log.info("解析令牌失败, 返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN_INVALID_TOKEN");
            resp.getWriter().write(objectMapper.writeValueAsString(error));
            return false;
        }

        //6.jwt解析成功但是已过期，返回令牌过期信息
        if (claims.getExpiration().before(new Date())) {
            log.info("令牌已过期");
            Result error = Result.error("您的令牌已过期，请重新登陆。TOKEN_EXPIRED");
            resp.getWriter().write(objectMapper.writeValueAsString(error));
            return false;
        }

        //6.放行。
        log.info("令牌合法, 放行");
        return true;
    }
}

