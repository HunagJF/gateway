package com.gateway.service.impl;

import com.gateway.bean.AuditLog;
import com.gateway.mapper.log.LogMapper;
import com.gateway.service.IAuditLoggerSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志处理类
 */
@Service
public class AuditLoggerSender implements IAuditLoggerSender {

    /**
     * 日志发送到指定服务，或者指定系统。Rest请求接口进行保存，或者打印，或者直接持久化到本地库，或者日志文件。等等
     * @param auditLog
     */
    private final LogMapper logMapper;

    @Autowired(required = true)
    public AuditLoggerSender(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public void send(AuditLog auditLog) {
        logMapper.add(auditLog);
    }
}
