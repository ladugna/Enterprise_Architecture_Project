package retail.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import retail.domain.TraceRecord;
import retail.service.TraceRecordServiceImpl;

import java.time.LocalDateTime;

@Aspect
@Configuration
@Slf4j
public class TraceRecordAdvice {
    @Autowired
    TraceRecordServiceImpl traceRecordService;
    @Around("execution(* retail.*.*.*(..))")
    public Object TraceAdvice(ProceedingJoinPoint call) throws Throwable {
        Object retVal = null;
        try {
            retVal = call.proceed();
        } catch (Exception e) {
            TraceRecord traceRecord = new TraceRecord(call.getSignature().getName(), "error", e.getMessage(), LocalDateTime.now());
            traceRecordService.saveRecord(traceRecord);
            log.error(e.getMessage());
        }
        return retVal;
    }
}
