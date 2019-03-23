package ru.nntu.yajb.timer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimerAspect {

    @Around("@annotation(ru.nntu.yajb.timer.Timer) && execution(* *(..))")
    public Object showTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long stopTime = System.currentTimeMillis() - startTime;
        System.out.println(String.format("%s is finished in %s ms", getTargetAlias(joinPoint), stopTime));
        return result;
    }

    private String getTargetAlias(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }
}
