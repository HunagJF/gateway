package com.gateway.service.impl;

import com.gateway.bean.AuditLog;
import com.gateway.service.IAuditLogger;
import com.gateway.service.ILogConsumerAsyncExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
@Service
public class AuditLoggerService implements IAuditLogger {

    private static final int MAX_BLOCK_QUEUE_CAPACITY = 1000;

    private volatile BlockingQueue<AuditLog> LOG_QUEUE = new ArrayBlockingQueue<>(MAX_BLOCK_QUEUE_CAPACITY);

    @Autowired
    private ILogConsumerAsyncExecutor logConsumerAsyncExecutor;

    @Override
    public void init() {
        new Thread(new LogConsumer(LOG_QUEUE)).start();
    }

    @Override
    public void addAuditLog(AuditLog auditLog) {
        if (auditLog != null && !LOG_QUEUE.offer(auditLog)) {
            log.warn(" add Audit Log Failed");
        }
    }


    public class LogConsumer implements Runnable {
        private final BlockingQueue<AuditLog> queue;

        LogConsumer(BlockingQueue<AuditLog> logQueue) {
            queue = logQueue;
        }

        public void run() {
            AuditLog auditLog = null;

            while (true) {
                while (true) {
                    try {
                        auditLog = queue.take();
                        if (auditLog == null) {
                            continue;
                        }
                    } catch (InterruptedException e) {
                        log.error(" take log queue InterruptedException");
                    }

                    consume(auditLog);
                }
            }

        }

        public void consume(AuditLog auditLog) {
            try {
                logConsumerAsyncExecutor.asyncConsume(auditLog);
            } catch (Exception e) {
                log.error(" asyncConsume exception ", e);
            }
        }
    }
}
