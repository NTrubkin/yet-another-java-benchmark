package ru.nntu.yajb.aspect.benchmark;

import com.google.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
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

import static ru.nntu.yajb.aspect.benchmark.CollectingMode.ALL;
import static ru.nntu.yajb.aspect.benchmark.CollectingMode.PAYLOADED_META;


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

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        MetaData metaData = new MetaData(
                getClassType(joinPoint),
                getMethodName(methodSignature),
                getArgumentTypes(methodSignature),
                getReturnType(methodSignature),
                startTime,
                runTime,
                Thread.currentThread().getName(),
                // todo: implement thread time benchmark
                THREAD_TIME_STUB
        );
        BenchmarkData data = new BenchmarkData(metaData);
        if (collectionModeAny(methodSignature, ALL, PAYLOADED_META)) {
            data.setPayloadData(collectPayloadData());
        }
        benchmarkDataService.put(data);
        return result;
    }

    private String getClassType(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSourceLocation().getWithinType().getName();
    }

    private String getMethodName(Signature signature) {
        return signature.getName();
    }

    private List<String> getArgumentTypes(MethodSignature methodSignature) {
        return Arrays.stream(methodSignature.getParameterTypes())
                .map(Class::getCanonicalName)
                .collect(Collectors.toList());
    }

    private String getReturnType(MethodSignature methodSignature) {
        return methodSignature.getReturnType().getCanonicalName();
    }

    private boolean collectionModeAny(MethodSignature methodSignature, CollectingMode... modes) {
        CollectingMode type = methodSignature.getMethod().getAnnotation(Benchmark.class).collect();
        return Arrays.asList(modes).contains(type);
    }

    private PayloadData collectPayloadData() {
        // todo: implement this
        return new PayloadData();
    }
}
