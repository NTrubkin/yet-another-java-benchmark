package ru.nntu.yajb.aspect.benchmark;

import com.google.inject.Inject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.Meta;
import ru.nntu.yajb.model.Payload;
import ru.nntu.yajb.service.data.BenchmarkService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.nntu.yajb.aspect.benchmark.CollectingMode.PAYLOADED_META;


@Aspect
public class BenchmarkAspect {
	private static final long THREAD_TIME_STUB = -1;

	@Inject
	private BenchmarkService benchmarkService;

	@Around("@annotation(ru.nntu.yajb.aspect.benchmark.Benchmark) && execution(* *(..))")
	public Object performBenchmark(ProceedingJoinPoint joinPoint) throws Throwable {
		BenchmarkData data = benchmarkService.initBenchmark();
		long startTime = System.currentTimeMillis();

		Object result = joinPoint.proceed();

		// todo: replace to nanoTime?
		long runTime = System.currentTimeMillis() - startTime;

		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Meta meta = data.getMeta();
		meta.setClassType(getClassType(joinPoint));
		meta.setMethodName(getMethodName(methodSignature));
		meta.setArgumentTypes(getArgumentTypes(methodSignature));
		meta.setReturnType(getReturnType(methodSignature));
		meta.setMethodStartTime(startTime);
		meta.setMethodRunTime(runTime);
		meta.setThreadName(Thread.currentThread().getName());
		// todo: implement thread time benchmark
		meta.setThreadRunTime(THREAD_TIME_STUB);
		// todo: handle possible exceptions
		meta.setThrewException(false);

		CollectingMode mode = methodSignature.getMethod().getAnnotation(Benchmark.class).collect();
		if (mode == PAYLOADED_META) {
			data.setPayload(collectPayloadData());
		}

		benchmarkService.reportBenchmark(data);
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

	private Payload collectPayloadData() {
		// todo: implement this
		return new Payload();
	}
}
