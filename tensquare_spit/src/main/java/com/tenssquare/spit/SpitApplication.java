package com.tenssquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/22 12:13
 * @Description: 吐槽启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }


    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

}
