package com.dcits.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
@Component
public class RedisUtil {
    //添加redis操作句柄
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置key-value
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置带过期时间的key-value
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * setnx,如果不存在key设置成功，存在key则设置失败
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean setIfAbsent(String key, Object value, long timeout, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, unit);
    }

    /**
     * 根据key值获取value值
     * @param key
     * @param T
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<?> T) {
        return (T) redisTemplate
                .opsForValue().get(key);
    }

    /**
     * 根据key值获取value值，value值为String
     * @param key
     * @return
     */
    public String get(String key) {
        return (String) redisTemplate
                .opsForValue().get(key);
    }

    /**
     * 值减一
     * @param key
     * @return
     */
    public Long decr(String key) {
        return redisTemplate
                .opsForValue().decrement(key);
    }

    /**
     * 值减去输入参数
     * @param key
     * @param delta
     * @return
     */
    public Long decr(String key, long delta) {
        return redisTemplate
                .opsForValue().decrement(key, delta);
    }

    /**
     * 值加一
     * @param key
     * @return
     */
    public Long incr(String key) {
        return redisTemplate
                .opsForValue().increment(key);
    }

    /**
     * 值加上输入参数值
     * @param key
     * @param delta
     * @return
     */
    public Long incr(String key, long delta) {
        return redisTemplate
                .opsForValue().increment(key, delta);
    }

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @param unit
     */
    public void expire(String key, long time, TimeUnit unit) {
        redisTemplate.expire(key, time, unit);
    }

}
