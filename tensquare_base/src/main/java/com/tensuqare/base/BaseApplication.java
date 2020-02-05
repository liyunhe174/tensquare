package com.tensuqare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/18 21:58
 * @Description: base启动类
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
