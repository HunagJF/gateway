package com.gateway.service;

import com.gateway.bean.AuditLog;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 日志处理支撑类
 */
@Component
public class LoggerSupport implements ApplicationContextAware {
    private static final String auditLoggerService = "auditLoggerService";
    private static IAuditLogger auditLogger;

    /**
     * 添加日志，到auditLoggerService 服务日志处理队列中。
     *
     * @param log
     */
    public static void addAuditLog(AuditLog log) {
        auditLogger.addAuditLog(log);
    }

    /**
     * 从Spring容器中获取 指定服务，并初始化LoggerSupport 的auditLogger实例对象
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        auditLogger = (IAuditLogger) applicationContext.getBean(auditLoggerService);
        //执行auditLoggerService初始化方法。用于启动auditLoggerService的队列处理线程。
        auditLogger.init();
    }
}
