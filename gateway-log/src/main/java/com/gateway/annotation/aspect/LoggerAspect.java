package com.gateway.annotation.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateway.annotation.Logger;
import com.gateway.bean.ExceptionOperLog;
import com.gateway.bean.OperationLog;
import com.gateway.utils.IdWorkerUtil;
import com.gateway.service.LoggerSupport;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class LoggerAspect {
  private static final ObjectMapper mapper = new ObjectMapper();

    @Pointcut("@annotation(com.gateway.annotation.Logger)")
    public void logger() {
    }

    @Around("logger()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object ret = pjp.proceed();
        OperationLog operLog = buildOperationLog(pjp, ret);
       // log.info(writeValueAsString(operLog));
        LoggerSupport.addAuditLog(operLog);
        return ret;

    }

    @AfterThrowing(value = "logger()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        // 保存异常日志记录
        OperationLog operLog = buildExceptionOperationLog(joinPoint, throwable);
        LoggerSupport.addAuditLog(operLog);
    }

    private OperationLog buildExceptionOperationLog(JoinPoint joinPoint, Throwable t) {
        ExceptionOperLog operLog = new ExceptionOperLog(buildOperationLog(joinPoint, null));
        operLog.setExceptionName(t.getClass().getName()); // 异常名称
        operLog.setExceptionMessage(stackTraceToString(t.getClass().getName(), t.getMessage(), t.getStackTrace())); // 异常信息
        return operLog;
    }

    private OperationLog buildOperationLog(JoinPoint joinPoint, Object ret){
        ObjectMapper objectMapper = new ObjectMapper();
        OperationLog operLog = new OperationLog();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        operLog.setTranceId(IdWorkerUtil.nextId());
        operLog.setStartTime(new Date());
        operLog.setUrl(request.getRequestURL().toString());
        operLog.setMethod(request.getMethod());

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取操作日志注解信息
        Logger loggerAnnotation = method.getAnnotation(Logger.class);
        if (loggerAnnotation != null) {
            operLog.setSource(loggerAnnotation.operSource());
            operLog.setSeverity(loggerAnnotation.severity());
            operLog.setOperName(loggerAnnotation.operName());
            operLog.setPersistence(loggerAnnotation.isPersistence());
        }
        operLog.setRequestMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operLog.setRemoteAddr(request.getRemoteAddr());
        operLog.setArgs(writeValueAsString(request.getParameterMap()));
        operLog.setEndTime(new Date());
        try {
            //获取请求头中的User-Agent
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
            operLog.setUserAgent(objectMapper.writeValueAsString(userAgent));
            // pjp.proceed()：当我们执行完切面代码之后，还有继续处理业务相关的代码。proceed()方法会继续执行业务代码，并且其返回值，就是业务处理完成之后的返回值。
            operLog.setResult(objectMapper.writeValueAsString(ret));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operLog;
    }

    private String writeValueAsString(Object object){
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
           log.error("object covert json exception,",e);
        }
        return json;
    }
    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }
}
