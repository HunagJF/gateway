package com.gateway.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ExceptionOperLog extends OperationLog {

    private String exceptionName;
    private String exceptionMessage;
    public ExceptionOperLog(OperationLog operlog) {
        this.setTranceId(operlog.getTranceId());
        this.setStartTime(operlog.getStartTime());
        this.setUrl(operlog.getUrl());
        this.setMethod(operlog.getMethod());
        this.setSource(operlog.getSource());
        this.setSeverity(operlog.getSeverity());
        this.setOperName(operlog.getOperName());

        this.setRequestMethod(getRequestMethod());
        this.setRemoteAddr(operlog.getRemoteAddr());
        this.setArgs(operlog.getArgs());
        this.setUserAgent(operlog.getUserAgent());

        this.setEndTime(operlog.getEndTime());
        this.setResult(null);
    }
}
