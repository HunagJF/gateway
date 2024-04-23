package com.gateway.annotation;

import com.gateway.enums.LogSeverity;

import java.lang.annotation.*;

/**
 *     private String source; // 日志来源 操作模块
 *     private String operType; // 日志级别
 *     private String operName; // 日志操作名称
 *     private boolean isPersistence; // 是否持久化日志信息
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {

    /**
     * 操作模块
     *
     * @return
     */
    String operSource() default "";

    /**
     * 操作类型
     *
     * @return
     */
    LogSeverity severity() default LogSeverity.WARNING;

    /**
     * 操作说明
     *
     * @return
     */
    String operName() default "";

    /**
     * 是否 持久化(persistence)
     *
     * @return
     */
    boolean isPersistence() default false;
}
