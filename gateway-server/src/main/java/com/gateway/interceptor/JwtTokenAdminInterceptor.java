package com.gateway.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.constant.AppConstant;
import com.gateway.properties.JwtProperties;
import com.gateway.result.Result;
import com.gateway.utils.CommonsUtil;
import com.gateway.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtTokenAdminInterceptor(JwtProperties jwtProperties, JwtUtil jwtUtil) {
        this.jwtProperties = jwtProperties;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //从请求头中获取令牌
        String token = CommonsUtil.getUserToken();

        //校验令牌
//        if (StringUtils.isNotEmpty(token) && jwtUtil.isTokenExpired(token)) {
//            // 检查是否需要刷新令牌
//            if (jwtUtil.shouldRefreshToken(token)) {
//                String refreshedToken = jwtUtil.generateToken(jwtUtil.extractUsername(token));
//                response.setHeader(AppConstant.AUTHORIZATION, refreshedToken);
//            }
//            return true;
//        }
        Result<Void> result = Result.error401("用户没有权限（令牌、用户名、密码错误）!");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        return false;
    }
}
