package com.dept01.bitfleamarket;

import com.dept01.bitfleamarket.mapper.user.VerificationCodeMapper;
import com.dept01.bitfleamarket.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class jwttest {

    @Test
    void jwtTest(){
        //获取用户 ID（假设用户 ID 存储在请求头中）
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJVc2VySWQiOjI1LCJleHAiOjE3MTgyMTAwODl9.Po446ET86CPSZ7r89pgHkqHU14e-a-Ywc0MvnrTSw7k";
        Claims jwt_claims = JwtUtils.parseJWT(jwt);
        int fromId = jwt_claims.get("UserId", Integer.class);
        System.out.println(fromId);
    }

}
