package com.gateway.mapper.log;

import com.gateway.bean.AuditLog;

public interface LogMapper {

    void add(AuditLog auditLog);
}
