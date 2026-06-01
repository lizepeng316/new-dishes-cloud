package com.etoak.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 读取 upload-service.yml 中以 upload 开头的配置
 */
@Component
@ConfigurationProperties("image")
@Data
public class ImageProperties {

    /**
     * 图片访问地址前缀
     */
    private String prefix;

    /**
     * 图片上传目录
     */
    private String location;

    /**
     * 支持图片类型
     */
    private List<String> typeList;
}