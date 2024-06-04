package com.dept01.bitfleamarket.controller;

import com.dept01.bitfleamarket.utils.OBSUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
public class UploadImageController {

    // 这是一个上传图片的示例，请根据需求修改SERVICE

    @Autowired
    private OBSUploadUtils obsUploadUtils;

    @PostMapping("/uploadAvatar")
    public String uploadAvatar(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        //调用华为云OBS工具类进行文件上传
        Map<String, Object> returnMap = obsUploadUtils.uploadAvatar(image);
        log.info("文件上传完成,文件访问的url: {}", returnMap.get("url"));

        // 返回的map格式封装如下
            // 返回上传成功信息
        // Map<String, Object>,注意如果需要String格式将Object.toString()
//            returnMap.put("code", 0);
//            returnMap.put("msg", "Upload avatar success");
//            returnMap.put("url")

        return returnMap.get("url").toString();
    }

    @PostMapping("/uploadProductImage")
    public String uploadProductImage(MultipartFile image) throws IOException {
        log.info("文件上传, 文件名: {}", image.getOriginalFilename());

        //调用华为云OBS工具类进行文件上传
        Map<String, Object> returnMap = obsUploadUtils.uploadProductImage(image);
        log.info("文件上传完成,文件访问的url: {}", returnMap.get("url"));

        return returnMap.get("url").toString();
    }
}
