package com.tensquare.qa.config;

import com.tensquare.qa.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 14:10
 * @Description:
 */
@Component
public class ApplictionConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");

    }
}
