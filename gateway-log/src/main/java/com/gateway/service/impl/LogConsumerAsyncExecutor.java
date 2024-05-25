package com.gateway.service.impl;

import com.gateway.bean.AuditLog;
import com.gateway.service.ILogConsumerAsyncExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogConsumerAsyncExecutor implements ILogConsumerAsyncExecutor {

    @Autowired
    private AuditLoggerSender auditLoggerSender;

    @Async("logAsyncExecutor")
    @Override
    public void asyncConsume(AuditLog auditLog) {
//        log.info(auditLog.toString());
        auditLoggerSender.send(auditLog);
    }
}
