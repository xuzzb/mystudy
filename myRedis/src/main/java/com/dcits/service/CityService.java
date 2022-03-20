package com.dcits.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.common.RedisKeyPrefixInfo;
import com.dcits.common.RedisUtil;
import com.dcits.entity.City;
import com.dcits.mapper.CityMapper;
import jodd.util.StringUtil;
import org.checkerframework.checker.units.qual.A;
import org.redisson.Redisson;
import org.redisson.RedissonReadWriteLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
@Service
public class CityService {
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Redisson redisson;

    public static final Integer CITY_CACHE_TIMEOUT = 60 * 60 * 24;//缓存持续时间，一天
    public static final String EMPTY_CACHE = "{}";//空缓存
    //热点并发重建
    public static final String LOCK_CITY_HOT_CACHE_CREATE_PREFIX = "lock:city:hot_cache_create:";
    //写锁
    public static final String LOCK_CITY_UPDATE_PREFIX = "lock:city:update:";
    //二级缓存
    public static Map<String,City> cityMap = new HashMap<String,City>();
    /**
     * 这里是新增一条记录，处理逻辑，新增到数据库，然后新增到缓存
     * 或者也可以不增加的缓存，但是会导致多去数据库查询
     * 这里不涉及一些奇奇怪怪的问题，由于是新增一条记录，所以也不存在操作同一条数据的可能，问题较少
     * @param city
     */
    @Transactional
    public City insertCityInfo(City city) {
        cityMapper.insertCityInfo(city);
        redisUtil.set(RedisKeyPrefixInfo.REDIS_KEY_PREFIX+city.getId()+"", JSON.toJSONString(city));
        return city;
    }

    /**
     * 更新数据
     * 更新数据库，同时更新数据库的缓存，此处会存在很多问题
     * 1、缓存击穿问题---加一个随机的时间，避免缓存击穿问题
     * 2、数据库缓存双写不一致问题---加读写锁
     * @param city
     */
    @Transactional
    public City updateCityInfo(City city){
        RReadWriteLock readWriteLock = redisson.getReadWriteLock(LOCK_CITY_UPDATE_PREFIX+city.getId());
        RLock writerLock = readWriteLock.writeLock();
        writerLock.lock();
        try {
            cityMapper.updateCityInfo(city);
            redisUtil.set(RedisKeyPrefixInfo.REDIS_KEY_PREFIX + city.getId() + "", JSON.toJSONString(city),
                    genCityCacheTimeout(), TimeUnit.SECONDS);
        }finally{
            writerLock.unlock();
        }
        return city;
    }
    /**
     * 更新数据
     * 更新数据库，同时更新数据库的缓存，此处会存在很多问题
     * 1、缓存击穿问题---加一个随机的时间，避免缓存击穿问题
     * 2、数据库缓存双写不一致问题---加读写锁
     * @param cityId
     */
    public City getCityIndo(int cityId){
        City city = null;//获取到的城市信息为空
        //首先从缓存里边获取,先获取缓存值
       String cityCacheString =  RedisKeyPrefixInfo.REDIS_KEY_PREFIX+cityId+"";
       city = getCityInfoByCache(cityCacheString);
       if(city != null){
           return city;
       }
       //此处加热点代码锁，保证热点代码并发重建减少问题

        RLock hotLock = redisson.getLock(LOCK_CITY_HOT_CACHE_CREATE_PREFIX + cityId);
        hotLock.lock();
        try {
            city = getCityInfoByCache(cityCacheString);

            if (city != null) {//如果获取到了缓存，那就返回相关信息
                return city;
            }
            //没获取到缓存，这里查询数据库
            // 如果此处，有多个线程查询，那么就会存在问题
            //加读锁，避免缓存数据库双写数据不一致问题
            RReadWriteLock readWriteLock = redisson.getReadWriteLock(LOCK_CITY_UPDATE_PREFIX + cityId);
            RLock readLock = readWriteLock.readLock();
            readLock.lock();
            try {
                city = cityMapper.getCityInfo(cityId);
                System.out.println("这是从数据库查询出来的信息"+city);
                cityMap.put(cityCacheString,city);
                //此处进行查询，如果查不到，就证明是空的，防止内存穿透
                if (city != null) {
                    redisUtil.set(RedisKeyPrefixInfo.REDIS_KEY_PREFIX +  cityId+ "", JSON.toJSONString(city),
                            genCityCacheTimeout(), TimeUnit.SECONDS);
                } else {
                    redisUtil.set(RedisKeyPrefixInfo.REDIS_KEY_PREFIX + cityId + "", JSON.toJSONString(EMPTY_CACHE),
                            genCityCacheTimeout(), TimeUnit.SECONDS);
                }
            } finally {
                readLock.unlock();
                System.out.println("解锁成功");
            }
        }finally {
            hotLock.unlock();
            System.out.println("解锁成功");
        }
        return city;
    }

    private Integer genCityCacheTimeout() {
        //加随机超时机制解决缓存批量失效(击穿)问题
        return CITY_CACHE_TIMEOUT + new Random().nextInt(5) * 60 * 60;
    }

  public City getCityInfoByCache(String cityInfoStr){
        City city = null;
        city = cityMap.get(cityInfoStr);
        if(city != null){
            System.out.println("这是查询的二级缓存"+city);
            return city;
        }
        String cityCacheInfo = redisUtil.get(cityInfoStr);//两种情况，空的，或者是{},如果是空的，证明没有，
         // 可能需要查询数据库，如果是{}，那么就是无效数据
        if(!StringUtil.isEmpty(cityCacheInfo)){//空的，查询数据库
             if(EMPTY_CACHE.equals(cityCacheInfo)){
             return  new City();
        }
        //获取到有相关的缓存信息
           Object object =  JSON.parse(cityCacheInfo);
        String s = object.toString();
            city =   JSONObject.parseObject(s, City.class);
           // city = JSON.parseObject(cityCacheInfo,City.class);//强行转换成city类型
        //对缓存时间进行一个随机延时，避免过期
        redisUtil.expire(cityInfoStr,genCityCacheTimeout(),TimeUnit.SECONDS);
      }
        if(city != null){
            System.out.println("这是从缓存中拿出来的信息"+city);
        }
      return city;
  }
}
