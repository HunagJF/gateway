package com.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 二级缓存
 */
@Component
@ConfigurationProperties(prefix = "sky.cache")
@Data
public class CacheProperties {

    private boolean enable;
}
