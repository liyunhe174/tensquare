package com.tensquare.user.config;

import com.tensquare.user.filter.JwtFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/29 10:50
 * @Description: 将拦截器添加到webmvc中
 */
@Component
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 添加拦截器
     * @param registry  拦截器注册对象
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                //添加拦截的规则  ， 也就是拦截所有
                .addPathPatterns("/**")
                //排除规则 ， 也就是不拦截那些路径
                .excludePathPatterns("/**/login");

    }
}
