package com.tensquare.friend.config;

import com.tensquare.friend.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 21:32
 * @Description:
 */
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
