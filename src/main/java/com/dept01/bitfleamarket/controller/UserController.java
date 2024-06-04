package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.pojo.user.User;
import com.dept01.bitfleamarket.service.UserService;
import com.dept01.bitfleamarket.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //获取个人信息
    @GetMapping("/users/{user_id}")
    public Result getUserInfo(@PathVariable String user_id) {
        User user = userService.getUserInfo(user_id);
        if (user == null){
            return Result.error((short)1,"用户不存在", "");
        }
        else{
            return Result.success(user);
        }
    }

    //修改个人信息
    @PutMapping("/users/{user_id}")
    public Result modifyUserInfo(@PathVariable String user_id, @RequestBody User user_in) {
        User user = userService.getUserInfo(user_id);
        if (user == null){
            return Result.error((short)1,"用户不存在", "");
        }
        else{
            userService.modifyUserInfo(user_id, user_in);
            return Result.success();
        }
    }

    //上传头像
    @PostMapping("/users/{user_id}/uploadAvatar")
    public Result uploadAvatar(@PathVariable String user_id, @RequestParam("img") MultipartFile avatar) {
        User user = userService.getUserInfo(user_id);
        if (user == null){
            return Result.error((short)1,"用户不存在", "");
        }
        else{
            return Result.success(userService.uploadAvatar(user_id, avatar));
        }
    }
}
