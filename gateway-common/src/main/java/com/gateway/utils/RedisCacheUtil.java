package com.gateway.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Redis缓存工具类
 * 提供对Redis缓存的基本操作，如添加、获取、删除和清空缓存。
 */
@Component
@Slf4j
public class RedisCacheUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 构造方法，注入RedisTemplate
     *
     * @param redisTemplate 用于执行Redis操作的RedisTemplate实例
     */
    @Autowired
    public RedisCacheUtil(RedisTemplate<String, Object> redisTemplate) {
        log.info("开始初始化Redis缓存工具类...");
        this.redisTemplate = redisTemplate;
    }

    /**
     * 将数据放入缓存
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     * @param value 缓存值
     * @param <T> 缓存值的类型
     */
    public <T> void putToCache(String cacheName, Object key, T value) {
        redisTemplate.opsForHash().put(cacheName, key, value);
    }

    /**
     * 从缓存获取数据
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     * @param type 缓存值的类型
     * @param <T> 缓存值的类型
     * @return 一个Optional对象，包含缓存值
     */
    public <T> Optional<T> getFromCache(String cacheName, Object key, Class<T> type) {
        Object value = redisTemplate.opsForHash().get(cacheName, key);
        if (value != null) {
            return Optional.of(type.cast(value)); // 将获取到的值转换为指定类型
        }
        return Optional.empty();
    }

    /**
     * 从缓存中删除数据
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     */
    public void evictFromCache(String cacheName, Object key) {
        redisTemplate.opsForHash().delete(cacheName, key);
    }

    /**
     * 清空指定缓存
     *
     * @param cacheName 缓存名称
     */
    public void clearCache(String cacheName) {
        redisTemplate.delete(cacheName);
    }
}
