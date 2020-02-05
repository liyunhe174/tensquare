package com.tensquare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/31 22:27
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class Webapplication {
    public static void main(String[] args) {
        SpringApplication.run(Webapplication.class, args);

    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
