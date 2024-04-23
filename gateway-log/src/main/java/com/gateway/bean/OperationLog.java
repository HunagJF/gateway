package com.gateway.bean;

import com.gateway.enums.LogSeverity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class OperationLog extends AuditLog{
    //操作日志ID
    private long tranceId;
    private String source; // 日志来源 操作模块
    private LogSeverity severity; // 日志级别
    private String operName; // 日志操作名称
    private boolean isPersistence; // 是否持久化日志信息

    private String url;
    private String method;
    private String remoteAddr;
    private String requestMethod;
    private String args;
    private String userAgent;
    private String result;
    private Date startTime;
    private Date endTime;
}
