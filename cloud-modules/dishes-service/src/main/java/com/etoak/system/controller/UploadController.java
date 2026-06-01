package com.etoak.system.controller;


import com.etoak.common.vo.ResultVO;
import com.etoak.system.api.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * 上传接口
     */
    @PostMapping("/image")
    public ResultVO<String> upload(MultipartFile pic) throws IOException {
        return uploadService.upload(pic);
    }
}
