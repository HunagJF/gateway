package com.gateway.constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局常量定义类
 */
@Slf4j
public class AppConstant {

    public AppConstant() {
        log.info("开始初始化全局常量定义类...");
    }

    /**
     * 获取用户
     */
    public static final String USER = "USER";

    /**
     * 获取Header的token
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 限流缓存名称
     */
    public static final String RATE_LIMIT_CACHE_NAME = "rateLimitCache";

}
