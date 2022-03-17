package com.dcits.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author xuzzhh
 * @Date 2021/8/13 21:27
 * @Version 1.0
 * @Since study redis的配置类，加载相关的配置文件
 */
//声明配置类注解
@Configuration
@PropertySource("classpath:application.yml")//声明相关配置文件加载路径
public class RedisConfig {
    @Value("172.17.0.5")
    private String host;//主机名称
    @Value("6379")
    private int port;//链接端口
    @Value("30000")
    private int timeout;//超时时间
    @Value("10")
    private int maxIdle;
    @Value("1500")
    private int maxWaitMills;//最大等待时间
    @Value("false")
    private Boolean blockeWhenExhausted;
    @Value("true")
    private Boolean JmxEnabled;
    @Bean
    public JedisPool jedisPoolFacotry(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMills);
        //链接阻塞时是否报异常false 报异常，true阻塞到获取
        jedisPoolConfig.setBlockWhenExhausted(blockeWhenExhausted);
        jedisPoolConfig.setJmxEnabled(JmxEnabled);//是否启用Pool的JMX管理功能
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout);
        return jedisPool;
    }
}
