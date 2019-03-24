package ru.nntu.yajb.aspect.timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TimerAspect {
    private static final Logger LOG = LoggerFactory.getLogger(TimerAspect.class);

    @Around("@annotation(ru.nntu.yajb.aspect.timer.Timer) && execution(* *(..))")
    public Object showTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        LOG.info("");
        long stopTime = System.currentTimeMillis() - startTime;
        LOG.info(String.format("%s is finished in %s ms", getTargetAlias(joinPoint), stopTime));
        return result;
    }

    private String getTargetAlias(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }
}
