package com.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 开启本地缓存或redis切换
 */
@Component
@ConfigurationProperties(prefix = "cache")
@Data
public class CacheProperties {

    private boolean useRedis;
}
