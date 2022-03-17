package com.dcits.controller;

import com.dcits.test.RedisString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xuzzb
 * @Create 2022/3/14
 * 这个服务是用来进行单机链接的redis
 */
@RestController
public class ProductController {

    @Resource
    public RedisString redisString;
    @RequestMapping("/deduct_stock")
    public String deductStock() {
        redisString.getValue("xu");
        return "test";
    }
}
