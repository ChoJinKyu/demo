package me.ckcho.demo.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {
    
    @Around("@annotation(LogWriteExeTime)")
    public Object logWriteExeTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();;

        Object proceed = joinPoint.proceed();

        sw.stop();
        log.info(sw.prettyPrint());

        return proceed;
    }
}