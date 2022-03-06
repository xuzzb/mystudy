package com.dcits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

}