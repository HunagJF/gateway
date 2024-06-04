package com.gateway.config;

import com.gateway.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;

import java.time.Duration;

/**
 * Redis 配置类
 * 配置 Redis 连接工厂和 RedisTemplate。
 */
@Configuration
@Slf4j
public class RedisConfig {

    private final RedisProperties redisProperties; // Redis 配置属性

    /**
     * 构造方法，注入 RedisProperties
     *
     * @param redisProperties 包含 Redis 连接信息的属性类
     */
    @Autowired
    public RedisConfig(RedisProperties redisProperties) {
        log.info("开始初始化 Redis 配置类...");
        this.redisProperties = redisProperties;
    }

    /**
     * 配置 Redis 连接工厂
     *
     * @return RedisConnectionFactory 实例
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());

        // 配置连接池
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder poolingConfig = LettucePoolingClientConfiguration.builder()
                .poolConfig(redisProperties.getLettuce().getPool().toGenericObjectPoolConfig());

        // 配置超时
        poolingConfig.commandTimeout(Duration.ofMillis(redisProperties.getTimeout()));

        LettuceClientConfiguration clientConfig = poolingConfig.build();

        RedisConnectionFactory connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);

        // 测试连接
        testConnection(connectionFactory);

        return connectionFactory;
    }

    /**
     * 配置 RedisTemplate
     *
     * @param redisConnectionFactory Redis 连接工厂
     * @return RedisTemplate 实例
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    /**
     * 测试 Redis 连接
     *
     * @param connectionFactory Redis 连接工厂
     */
    private void testConnection(RedisConnectionFactory connectionFactory) {
        RedisConnection connection = null;
        try {
            connection = connectionFactory.getConnection();
            log.info("Redis 连接成功！");
            // 这里可以添加一些测试操作，如设置和获取值
        } catch (Exception e) {
            log.error("Redis 连接失败！", e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
