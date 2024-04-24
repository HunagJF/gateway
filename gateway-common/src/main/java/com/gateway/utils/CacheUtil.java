package com.gateway.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 开启二级缓存
 */
@Component
public class CacheUtil {

    private final CacheManager cacheManager;

    @Autowired
    public CacheUtil(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 添加缓存
     * @param cacheName
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void putToCache(String cacheName, Object key, T value) {
        getCache(cacheName).put(key, value);
    }

    /**
     * 从缓存获取
     * @param cacheName
     * @param key
     * @param type
     * @return
     * @param <T>
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> getFromCache(String cacheName, Object key, Class<T> type) {
        Cache.ValueWrapper valueWrapper = getCache(cacheName).get(key);
        if (valueWrapper != null) {
            return Optional.of((T) valueWrapper.get());
        }
        return Optional.empty();
    }

    /**
     * 从缓存删除
     * @param cacheName
     * @param key
     */
    public void evictFromCache(String cacheName, Object key) {
        getCache(cacheName).evict(key);
    }

    /**
     * 清空指定缓存
     * @param cacheName
     */
    public void clearCache(String cacheName) {
        getCache(cacheName).clear();
    }

    private Cache getCache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            throw new IllegalArgumentException("Cache with name '" + cacheName + "' not found");
        }
        return cache;
    }
}

