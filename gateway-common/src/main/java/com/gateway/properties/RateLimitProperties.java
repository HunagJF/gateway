package com.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rate-limit")
@Data
public class RateLimitProperties {
    private long timeInterval;
    private int maxCount;
}
