package com.dcits.test;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author xuzzhh
 * @Date 2021/8/13 21:52
 * @Version 1.0
 * @Since study
 */
@Component
public class RedisString {
    public static String REDIS_KEY = "test:redis";
    @Autowired
    private JedisPool jedisPool;

    public String setKey(String key,String value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(REDIS_KEY + key, value);
        }catch(Exception e){
            throw new RuntimeException("插入redis失败");
        }finally {
            jedis.close();
        }
    }

    public String getValue(String key){
        Jedis jedis = null;
        String value = "";
        try{

            jedis = jedisPool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            throw new RuntimeException("从redis获取值失败");
        }finally {
            jedis.close();
        }
        return value;
    }

}
