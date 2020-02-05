package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
           1、authorizeRequests所有security全注解配置实现的开端，表示开始说明需要的权限。
           2、需要的权限分两个部分，第一部分是拦截的路径。第二部分访问该路径所需要的权限。
           3、antMatchers表示拦截什么路径，permitAll任何权限都可以访问，也就是直接放行
           4、anyRequest任何请求，authenticated认证后才能访问
           5、and().csrf().disable();固定写法，表示使csrf拦截失效
         */
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
