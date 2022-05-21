package com.dcits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author xuzzb
 * @Create 2022/5/16
 */
@SpringBootApplication
@MapperScan("com.dcits")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
