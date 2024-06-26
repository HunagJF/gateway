package com.gateway.constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 信息提示常量类
 */
@Slf4j
public class MessageConstant {

    /**
     * 操作成功的消息
     */
    public static final String OPERATION_SUCCESS_MESSAGE = "操作成功！";

    /**
     * 登录成功的消息
     */
    public static final String LOGIN_SUCCESS_MESSAGE = "登录成功！";

    /**
     * 注销成功的消息
     */
    public static final String LOGOUT_SUCCESS_MESSAGE = "注销成功！";

    /**
     * 用户没有权限的消息
     */
    public static final String UNAUTHORIZED_MESSAGE = "用户没有权限（令牌、用户名、密码错误）！";

    /**
     * 限流提示
     */
    public static final String RATE_LIMIT_EXCEEDED = "您的请求已被限流，请稍后再试";


    private MessageConstant() {
        log.info("开始初始化信息提示常量类...");
    }

}
