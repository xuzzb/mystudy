package com.dcits.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author xuzzb
 * @Create 2022/3/14
 * 这个服务是用来进行单机链接的redis
 */
@RestController
public class ProductController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;//redis操作句柄
    @Autowired
    private Redisson redisson;//加分布式锁
    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "lock:product_001";//锁的key用商品名称
        //此处存在一个问题，如果加了锁，但是没有释放，就导致锁无法释放，
        String clientId = UUID.randomUUID().toString();
//        Boolean result = stringRedisTemplate.opsForValue().
//                setIfAbsent(lockKey,clientId,
//                1000, TimeUnit.SECONDS);
        //此处设置过期时间stringRedisTemplate.expire(lockKey, 10000); 优化到在设置值的时候，就使用过期时间
//

        RLock redissonLock = redisson.getLock(lockKey);//使用redisson进行加锁
        redissonLock.lock();
        //此处如果是单机可以保证并发安全，但是集群就无法保证了
       try {//避免出现了异常，无法及时释放锁
           synchronized (this) {
               int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(("stock")));
               if (stock > 0) {
                   int realStock = stock - 1;
                   stringRedisTemplate.opsForValue().set("stock", realStock + "");
                   System.out.println("剩余库存" + realStock);
               }
           }
       }catch (Exception e){
           System.out.println("此处执行出现了异常");
       }finally {
           //此处还是存在问题，如果并发高，执行业务时间长，比过期时间还长，会导致释放的不是自己的锁
           //处理办法,生成一个UUID，然后用UUID作为值进行判断
           if(clientId.equals(stringRedisTemplate.opsForValue().get("stock"))){
               //此处依旧存在问题，如果执行这个删除的时候，恰巧过期，
               // 别的线程又恰巧加了锁，这里释放锁，又会把别的线程锁释放
               stringRedisTemplate.delete(lockKey);//释放锁
           }
           //stringRedisTemplate.delete(lockKey);//释放锁
           redissonLock.unlock();//释放锁
       }
        return "test";
    }


}
