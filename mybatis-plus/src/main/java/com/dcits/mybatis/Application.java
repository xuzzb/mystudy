package com.dcits.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xuzzb
 * @Create 2022/5/14
 */
@RestController
@SpringBootApplication
@MapperScan("com.dcits")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
