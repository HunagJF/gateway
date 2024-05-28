package com.gateway.aspect.login;

import com.gateway.constant.JwtClaimsConstant;
import com.gateway.dto.login.UserTypeDTO;
import com.gateway.properties.JwtProperties;
import com.gateway.result.Result;
import com.gateway.utils.CacheUtil;
import com.gateway.utils.JwtUtil;
import com.gateway.vo.login.UserTypeVO;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录
 * code等于0时生成JWT令牌
 */

@Aspect
@Component
public class UserLoginAspect {

    private final JwtProperties jwtProperties;
    private final CacheUtil cacheUtil;

    @Autowired
    public UserLoginAspect(JwtProperties jwtProperties, CacheUtil cacheUtil) {
        this.jwtProperties = jwtProperties;
        this.cacheUtil = cacheUtil;
    }

    @Pointcut("execution(public * com.gateway.controller.login.UserLoginController.login(..))")
    public void loginPointcut() {}

    @Around("loginPointcut()")
    public Object aroundLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法入参
        Object[] args = joinPoint.getArgs();
        UserTypeDTO userTypeDTO = (UserTypeDTO) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];

        // 继续执行方法
        Object result = joinPoint.proceed();

        if (result instanceof Result && ((Result) result).getCode() == 0) {
            UserTypeVO userTypeVO = (UserTypeVO) ((Result) result).getData();
            if (userTypeVO != null && StringUtils.isNotEmpty(userTypeVO.getUsername())) {
                // 登录成功后生成 JWT 令牌
                Map<String, Object> claims = new HashMap<>();
                claims.put(JwtClaimsConstant.USER, userTypeVO);
                String token = JwtUtil.createJWT(claims);

                response.setHeader("Authorization", token);
                cacheUtil.putToCache(JwtClaimsConstant.USER,token, userTypeVO);
            }
        }
        // 返回方法的原始返回值
        return result;
    }
}
