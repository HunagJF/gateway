package com.gateway.service;

import com.gateway.bean.AuditLog;

public interface ILogConsumerAsyncExecutor {
    void asyncConsume(AuditLog auditLog);
}
