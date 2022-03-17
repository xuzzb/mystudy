package com.dcits.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author xuzzhh
 * @Date 2021/8/13 22:02
 * @Version 1.0
 * @Since study
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedisString {

    @Resource
    public RedisString redisString;

    @Test
    public void testSet(){
        System.out.println("ssssssssssss");
        redisString.setKey("ssssss","sssss");
    }
    @Test
    public void testGetValue(){
        String key = "me";
        log.debug("开始从redis获取value值key为"+key);
         String value =  redisString.getValue(key);
        log.debug(value);
    }

}
