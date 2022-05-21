package com.dcits;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

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
