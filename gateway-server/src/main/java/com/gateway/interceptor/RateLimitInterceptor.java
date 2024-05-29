package com.gateway.interceptor;

import com.gateway.constant.AppConstant;
import com.gateway.constant.MessageConstant;
import com.gateway.properties.RateLimitProperties;
import com.gateway.result.Result;
import com.gateway.utils.CacheUtil;
import com.gateway.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * 请求限流拦截器
 */
@Component
@Slf4j
public class RateLimitInterceptor implements HandlerInterceptor {

    private final CacheUtil cacheUtil;

    private final RateLimitProperties rateLimitProperties;

    @Autowired
    public RateLimitInterceptor(CacheUtil cacheUtil, RateLimitProperties rateLimitProperties) {
        this.cacheUtil = cacheUtil;
        this.rateLimitProperties = rateLimitProperties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String ipAddress = request.getRemoteAddr();
        String requestPath = request.getRequestURI();
        String cacheName = AppConstant.RATE_LIMIT_CACHE_NAME + requestPath;
        // 从缓存中获取请求限制信息
        Optional<RateLimitInfo> rateLimitInfoOptional = cacheUtil.getFromCache(cacheName, ipAddress, RateLimitInfo.class);
        RateLimitInfo rateLimitInfo;
        if (rateLimitInfoOptional.isPresent()) {
            rateLimitInfo = rateLimitInfoOptional.get();
        } else {
            rateLimitInfo = new RateLimitInfo();
            cacheUtil.putToCache(cacheName, ipAddress, rateLimitInfo);
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - rateLimitInfo.getLastResetTime() > rateLimitProperties.getTimeInterval()) {
            // 一分钟过去后重置计数
            rateLimitInfo.reset(currentTime);
            cacheUtil.putToCache(cacheName, ipAddress, rateLimitInfo);
        }
        if (rateLimitInfo.getCount() >= rateLimitProperties.getMaxCount()) {
            // 如果请求次数超过限制，返回 false，拒绝请求
            ResponseUtil.writeJson(response, Result.error(MessageConstant.RATE_LIMIT_EXCEEDED));
            return false;
        }
        rateLimitInfo.incrementCount();
        cacheUtil.putToCache(cacheName, ipAddress, rateLimitInfo);
        return true;
    }

    // 内部类，用于存储请求限制信息
    private static class RateLimitInfo {
        private long lastResetTime;
        private int count;

        public RateLimitInfo() {
            this.lastResetTime = System.currentTimeMillis();
            this.count = 0;
        }

        // 重置计数器
        public synchronized void reset(long time) {
            this.lastResetTime = time;
            this.count = 0;
        }

        // 增加计数器
        public synchronized void incrementCount() {
            this.count++;
        }

        // 获取上次重置时间
        public long getLastResetTime() {
            return lastResetTime;
        }

        // 获取当前计数
        public int getCount() {
            return count;
        }
    }
}
