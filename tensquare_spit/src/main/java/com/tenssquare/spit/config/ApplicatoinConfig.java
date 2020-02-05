package com.tenssquare.spit.config;

import com.tenssquare.spit.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 14:36
 * @Description:
 */
@Component
public class ApplicatoinConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
