package com.gateway.service;

import com.gateway.bean.AuditLog;

public interface IAuditLoggerSender {
    void send(AuditLog auditLog);
}
