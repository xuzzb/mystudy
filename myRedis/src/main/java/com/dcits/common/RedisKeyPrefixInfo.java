package com.dcits.common;

/**
 * @Author xuzzb
 * @Create 2022/3/17
 */
public interface RedisKeyPrefixInfo {
    /**
     * 商品信息，设置缓存添加前缀，用于区分不同产品
     */
    String REDIS_KEY_PREFIX = "city_cache";
}
