package com.dcits;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzhh
 * @Date 2021/8/13 22:35
 * @Version 1.0
 * @Since study
 */

@RestController
@SpringBootApplication
@MapperScan("com.dcits")
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }

    @Bean
    public Redisson redisson() {
        // 此为单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://172.17.0.5:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}