package com.gateway.service;

import com.gateway.bean.AuditLog;

public interface IAuditLogger {
    void init();
    void addAuditLog(AuditLog log);
}
