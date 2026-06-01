package com.etoak.common.pagehelper;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PageHelperConfig {
    @Bean
    public PageInterceptor pageInterceptor() {
        Properties properties = new Properties();
        /*
            分页合理化参数，默认值为false。
            当该参数设置为 true 时
            pageNum<=0 时会查询第一页，
            pageNum>pages（超过总数时），会查询最后一页。
            默认false 时，直接根据参数进行查询。
         */
        properties.setProperty("reasonable", "true");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
