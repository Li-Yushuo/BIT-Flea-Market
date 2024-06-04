package com.dept01.bitfleamarket.utils;

import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectRequest;
import com.obs.services.exception.ObsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 华为云 OBS 工具类
 */
@Component
public class OBSUploadUtils {

    @Value("${huaweicloud.obs.endpoint}")
    private String endpoint;
    @Value("${huaweicloud.obs.accessKeyId}")
    private String accessKeyId;
    @Value("${huaweicloud.obs.accessKeySecret}")
    private String accessKeySecret;
    @Value("${huaweicloud.obs.bucketName}")
    private String bucketName;

    /**
     * 实现上传头像到OBS
     */
    public Map<String, Object> uploadAvatar(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        fileName = "avatar/" + fileName;

        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endpoint);
        // 创建返回实例
        Map<String, Object> returnMap = new HashMap<>();

        // 上传文件到 OBS
        try {
            PutObjectRequest request = new PutObjectRequest();
            request.setBucketName(bucketName);
            request.setObjectKey(fileName);
            request.setInput(inputStream);
            obsClient.putObject(request);
            // 文件访问路径
            String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
            // 请求成功，打印上传成功信息
            System.out.println("putObject successfully");
            // 返回上传成功信息
            returnMap.put("code", 0);
            returnMap.put("msg", "Upload avatar success");
            returnMap.put("url", url);
        } catch (ObsException e) {
            System.out.println("putObject failed");
            // 请求失败,打印http状态码
            System.out.println("HTTP Code:" + e.getResponseCode());
            // 请求失败,打印服务端错误码
            System.out.println("Error Code:" + e.getErrorCode());
            // 请求失败,打印详细错误信息
            System.out.println("Error Message:" + e.getErrorMessage());
            // 请求失败,打印请求id
            System.out.println("Request ID:" + e.getErrorRequestId());
            System.out.println("Host ID:" + e.getErrorHostId());
            // 返回上传失败信息
            returnMap.put("code", e.getResponseCode());
            returnMap.put("msg", e.getErrorMessage());
            returnMap.put("url", "");
        } catch (Exception e) {
            System.out.println("putObject failed");
            // 返回上传失败信息
            returnMap.put("code", -1);
            returnMap.put("msg", "Unknown failed");
            returnMap.put("url", "");
        }

        // 返回map
        return returnMap;
    }
    public Map<String, Object> uploadProductImage(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        fileName = "productImage/" + fileName;

        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(accessKeyId, accessKeySecret, endpoint);
        // 创建返回实例
        Map<String, Object> returnMap = new HashMap<>();

        // 上传文件到 OBS
        try {
            PutObjectRequest request = new PutObjectRequest();
            request.setBucketName(bucketName);
            request.setObjectKey(fileName);
            request.setInput(inputStream);
            obsClient.putObject(request);
            // 文件访问路径
            String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
            // 请求成功，打印上传成功信息
            System.out.println("putObject successfully");
            // 返回上传成功信息
            returnMap.put("code", 0);
            returnMap.put("msg", "Upload avatar success");
            returnMap.put("url", url);
        } catch (ObsException e) {
            System.out.println("putObject failed");
            // 请求失败,打印http状态码
            System.out.println("HTTP Code:" + e.getResponseCode());
            // 请求失败,打印服务端错误码
            System.out.println("Error Code:" + e.getErrorCode());
            // 请求失败,打印详细错误信息
            System.out.println("Error Message:" + e.getErrorMessage());
            // 请求失败,打印请求id
            System.out.println("Request ID:" + e.getErrorRequestId());
            System.out.println("Host ID:" + e.getErrorHostId());
            // 返回上传失败信息
            returnMap.put("code", e.getResponseCode());
            returnMap.put("msg", e.getErrorMessage());
            returnMap.put("url", "");
        } catch (Exception e) {
            System.out.println("putObject failed");
            // 返回上传失败信息
            returnMap.put("code", -1);
            returnMap.put("msg", "Unknown failed");
            returnMap.put("url", "");
        }

        // 返回map
        return returnMap;
    }
}