package com.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {

    private String host;
    private int port;
    private String password;
    private int database;
    private long timeout;
    private Lettuce lettuce;

    @Data
    public static class Lettuce {
        private Pool pool;

        @Data
        public static class Pool {
            private int maxActive;
            private long maxWait;
            private int minIdle;
            private int maxIdle;

            public GenericObjectPoolConfig<Object> toGenericObjectPoolConfig() {
                GenericObjectPoolConfig<Object> config = new GenericObjectPoolConfig<>();
                config.setMaxTotal(maxActive);
                config.setMaxWaitMillis(maxWait);
                config.setMinIdle(minIdle);
                config.setMaxIdle(maxIdle);
                return config;
            }
        }
    }
}
