package com.gateway.utils;

import com.gateway.properties.CacheProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 结合本地缓存和Redis缓存的工具类，通过配置文件的开关来选择使用哪种缓存。
 */
@Component
@Slf4j
public class CombinedCacheUtil {

    private final CacheUtil cacheUtil; // 本地缓存工具类
    private final RedisCacheUtil redisCacheUtil; // Redis缓存工具类
    private final CacheProperties cacheProperties; // 缓存属性配置类

    @Autowired
    public CombinedCacheUtil(CacheUtil cacheUtil, RedisCacheUtil redisCacheUtil,
                             CacheProperties cacheProperties) {
        log.info("开始初始化CombinedCacheUtil类...");
        this.cacheUtil = cacheUtil;
        this.redisCacheUtil = redisCacheUtil;
        this.cacheProperties = cacheProperties;
    }

    /**
     * 添加缓存
     * 根据配置选择使用本地缓存或Redis缓存。
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     * @param value 缓存值
     * @param <T> 缓存值的类型
     */
    public <T> void putToCache(String cacheName, Object key, T value) {
        if (cacheProperties.isUseRedis()) {
            redisCacheUtil.putToCache(cacheName, key, value);
        } else {
            cacheUtil.putToCache(cacheName, key, value);
        }
    }

    /**
     * 从缓存获取值
     * 根据配置选择从本地缓存或Redis缓存中获取值。
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     * @param type 缓存值的类型
     * @param <T> 缓存值的类型
     * @return 一个Optional对象，包含缓存值
     */
    public <T> Optional<T> getFromCache(String cacheName, Object key, Class<T> type) {
        if (cacheProperties.isUseRedis()) {
            return redisCacheUtil.getFromCache(cacheName, key, type);
        } else {
            return cacheUtil.getFromCache(cacheName, key, type);
        }
    }

    /**
     * 从缓存中删除值
     * 根据配置选择从本地缓存或Redis缓存中删除值。
     *
     * @param cacheName 缓存名称
     * @param key 缓存键
     */
    public void evictFromCache(String cacheName, Object key) {
        if (cacheProperties.isUseRedis()) {
            redisCacheUtil.evictFromCache(cacheName, key);
        } else {
            cacheUtil.evictFromCache(cacheName, key);
        }
    }

    /**
     * 清空指定缓存
     * 根据配置选择清空本地缓存或Redis缓存。
     *
     * @param cacheName 缓存名称
     */
    public void clearCache(String cacheName) {
        if (cacheProperties.isUseRedis()) {
            redisCacheUtil.clearCache(cacheName);
        } else {
            cacheUtil.clearCache(cacheName);
        }
    }
}
