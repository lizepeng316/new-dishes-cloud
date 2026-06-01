package com.etoak.config;

import cn.hutool.core.util.StrUtil;
import com.etoak.common.properties.ImageProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

    ImageProperties imageProperties;

    public WebFluxConfiguration(ImageProperties imageProperties) {
        this.imageProperties = imageProperties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // mapping = /pics/**
        String mapping = imageProperties.getPrefix().endsWith(StrUtil.SLASH) ?
                imageProperties.getPrefix() + "**" :
                imageProperties.getPrefix() + "/**";

        String location = imageProperties.getLocation().endsWith(StrUtil.SLASH) ?
                imageProperties.getLocation() :
                imageProperties.getLocation() + StrUtil.SLASH;
        registry.addResourceHandler(mapping).addResourceLocations("file:" + location);
    }
}