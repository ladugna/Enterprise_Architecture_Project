package retail.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
@Slf4j
public class ServiceTimerAdvice {
    @Around("execution(* retail.service.*.*(..))")
    public Object time(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();

        long totaltime = sw.getTotalTimeMillis();
        log.info("Time to execute " + call.getSignature().getName() + " = " + totaltime + " ms");

        return retVal;
        //
    }

}
