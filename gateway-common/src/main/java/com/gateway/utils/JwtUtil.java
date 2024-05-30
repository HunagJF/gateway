package com.gateway.utils;

import com.gateway.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    private final JwtProperties jwtProperties;

    @Autowired
    public JwtUtil(JwtProperties jwtProperties) {
        log.info("开始初始化JwtUtil类...");
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成JWT令牌
     *
     * @param username 用户名
     * @return JWT令牌
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims) // 设置自定义声明
                .setSubject(username) // 设置主题为用户名
                .setIssuedAt(new Date(System.currentTimeMillis())) // 设置令牌签发时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration())) // 设置令牌过期时间
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()) // 使用HS512算法和密钥签名
                .compact(); // 压缩为JWT字符串
    }

    /**
     * 提取JWT声明
     *
     * @param token JWT令牌
     * @return JWT声明
     */
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecret()) // 设置用于验证签名的密钥
                .parseClaimsJws(token) // 解析JWT令牌
                .getBody(); // 获取声明体
    }

    /**
     * 检查令牌是否过期
     *
     * @param token JWT令牌
     * @return 是否过期
     */
    public boolean isTokenExpired(String token) {
        Claims claims = null;
        try {
            claims = extractClaims(token);
        } catch (Exception e) {
            return false; // 无法解析令牌，视为过期
        }
        return !claims.getExpiration().before(new Date());
    }

    /**
     * 提取用户名
     *
     * @param token JWT令牌
     * @return 用户名
     */
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    /**
     * 检查是否需要刷新令牌
     *
     * @param token JWT令牌
     * @return 是否需要刷新
     */
    public boolean shouldRefreshToken(String token) {
        Date expiration = extractClaims(token).getExpiration();
        return (expiration.getTime() - System.currentTimeMillis()) < jwtProperties.getRefreshThreshold();
    }
}
