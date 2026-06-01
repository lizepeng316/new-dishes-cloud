package com.etoak.system.api;


import com.etoak.common.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图片上传服务接口
 */
@FeignClient("upload-service")
public interface UploadService {

    /**
     * 声明上传接口
     * 注意 1、PostMapping 增加 consumes 属性, 属性值: multipart/form-data
     * 注意 2、参数 MultipartFile 前增加 @RequestPart
     */
    @PostMapping(value = "/upload/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> upload(@RequestPart MultipartFile pic) throws IOException;
}