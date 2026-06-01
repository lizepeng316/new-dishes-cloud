package com.etoak.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.properties.ImageProperties;
import com.etoak.common.vo.ResultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
public class UploadController {

    ImageProperties imageProperties;

    public UploadController(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    /**
     * 图片上传接口: post /upload/image
     */
    @PostMapping("/image")
    public ResultVO<String> upload(MultipartFile pic) throws IOException {
        if (ObjectUtils.isEmpty(pic) || pic.isEmpty()) {
            return ResultVO.failed("文件不能为空！");
        }

        if (!imageProperties.getTypeList().contains(pic.getContentType())) {
            String types = imageProperties.getTypeList().stream()
                    .map(type -> type.substring(type.lastIndexOf(StrUtil.SLASH) + 1))
                    .collect(Collectors.joining("、"));
            return ResultVO.failed("仅支持" + types + "类型的图片");
        }

        // 图片后缀
        String suffix = FileNameUtil.getSuffix(pic.getOriginalFilename());
        // 图片名称
        String filename = IdUtil.simpleUUID() + StrUtil.DOT + suffix;

        /* 创建目录 */
        File uploadDir = new File(imageProperties.getLocation());
        uploadDir.mkdirs();

        // 目标文件
        File destFile = new File(uploadDir, filename);

        // 上传
        pic.transferTo(destFile);

        String path = imageProperties.getPrefix().endsWith(StrUtil.SLASH) ?
                imageProperties.getPrefix() + filename :
                imageProperties.getPrefix() + StrUtil.SLASH + filename;
        return ResultVO.success(path);
    }
}