package ru.nntu.yajb.benchmark;

import com.google.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import ru.nntu.yajb.service.BenchmarkDataService;


@Aspect
public class BenchmarkAspect {

    @Inject
    private BenchmarkDataService benchmarkDataService;

    @Around("@annotation(ru.nntu.yajb.benchmark.Benchmark) && execution(* *(..))")
    public Object showTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        System.out.println("");
        Object result = joinPoint.proceed();

        long stopTime = System.currentTimeMillis() - startTime;
        System.out.println(String.format("%s is finished in %s ms", getTargetAlias(joinPoint), stopTime));
        System.out.println("");
        benchmarkDataService.put(null);
        return result;
    }

    private String getTargetAlias(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }
}
