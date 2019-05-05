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
		long runTime;
		Object result = null;
		Exception resultException = null;
		long startTime = System.nanoTime();

		try {
			startTime = System.nanoTime();
			result = joinPoint.proceed();
			runTime = System.nanoTime() - startTime;
		} catch (Exception e) {
			runTime = System.nanoTime() - startTime;
			resultException = e;
		}


		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Meta meta = data.getMeta();
		meta.setClassType(getClassType(joinPoint));
		meta.setMethodName(getMethodName(methodSignature));
		meta.setArgumentTypes(getArgumentTypes(joinPoint));
		meta.setReturnType(getReturnType(methodSignature));
		meta.setMethodStartTime(startTime);
		meta.setMethodRunTime(runTime);
		meta.setThreadName(Thread.currentThread().getName());
		// todo: implement thread time benchmark
		meta.setThreadRunTime(THREAD_TIME_STUB);
		meta.setThrewException(resultException != null);

		CollectingMode mode = methodSignature.getMethod().getAnnotation(Benchmark.class).collect();
		if (mode == PAYLOADED_META) {
			data.setPayload(collectPayloadData());
		}

		benchmarkService.reportBenchmark(data);

		if (resultException == null) {
			return result;
		}
		else {
			throw resultException;
		}
	}

	private String getClassType(ProceedingJoinPoint joinPoint) {
		return joinPoint.getSourceLocation().getWithinType().getName();
	}

	private String getMethodName(Signature signature) {
		return signature.getName();
	}

	private List<String> getArgumentTypes(ProceedingJoinPoint joinPoint) {
		return Arrays.stream(joinPoint.getArgs())
				.map(Object::getClass)
				.map(Class::getCanonicalName)
				.collect(Collectors.toList());
	}

	private String getReturnType(MethodSignature methodSignature) {
		// todo: fix to actual object type
		return methodSignature.getReturnType().getCanonicalName();
	}

	private Payload collectPayloadData() {
		// todo: implement this
		return new Payload();
	}
}
