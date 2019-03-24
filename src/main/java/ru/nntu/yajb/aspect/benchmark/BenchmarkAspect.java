package ru.nntu.yajb.aspect.benchmark;

import com.google.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.service.BenchmarkDataService;


@Aspect
public class BenchmarkAspect {
    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkAspect.class);

    @Inject
    private BenchmarkDataService benchmarkDataService;

    @Around("@annotation(ru.nntu.yajb.aspect.benchmark.Benchmark) && execution(* *(..))")
    public Object showTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long stopTime = System.currentTimeMillis() - startTime;
        LOG.info(String.format("%s is finished in %s ms", getTargetAlias(joinPoint), stopTime));
        benchmarkDataService.put(null);
        return result;
    }

    private String getTargetAlias(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toShortString();
    }
}
