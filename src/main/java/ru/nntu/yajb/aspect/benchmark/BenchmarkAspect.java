package ru.nntu.yajb.aspect.benchmark;

import com.google.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.MetaData;
import ru.nntu.yajb.model.PayloadData;
import ru.nntu.yajb.service.BenchmarkDataService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Aspect
public class BenchmarkAspect {
    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkAspect.class);
    private static final long THREAD_TIME_STUB = -1;

    @Inject
    private BenchmarkDataService benchmarkDataService;

    @Around("@annotation(ru.nntu.yajb.aspect.benchmark.Benchmark) && execution(* *(..))")
    public Object performBenchmark(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long runTime = System.currentTimeMillis() - startTime;
        MetaData metaData = new MetaData(
                getClassType(joinPoint),
                getMethodName(joinPoint),
                getArgumentTypes(joinPoint),
                getReturnType(joinPoint),
                startTime,
                runTime,
                Thread.currentThread().getName(),
                THREAD_TIME_STUB
        );
        PayloadData payloadData = new PayloadData();
        BenchmarkData data = new BenchmarkData(metaData, payloadData);
        benchmarkDataService.put(data);
        return result;
    }

    private String getClassType(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSourceLocation().getWithinType().getName();
    }

    private String getMethodName(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private List<String> getArgumentTypes(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return Arrays.stream(methodSignature.getParameterTypes())
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
    }

    private String getReturnType(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getReturnType().getCanonicalName();
    }
}
