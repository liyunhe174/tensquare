package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Auther: liyunhe
 * @Date: 2020/1/23 18:01
 * @Description: 搜索启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }


    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
