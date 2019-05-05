package ru.nntu.yajb.service;

import ru.nntu.yajb.util.BenchmarkException;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

// todo: add tests
public class ActiveBenchmarkServiceImpl implements ActiveBenchmarkService {

	private final Map<String, Deque<UUID>> activeBenchmarks = new HashMap<>();

	@Override
	public void initBenchmark(UUID benchmarkId, String threadName) {
		// todo: learn more about ConcurrentLinkedDeque
		Deque<UUID> stack = activeBenchmarks.computeIfAbsent(threadName, tn -> new ConcurrentLinkedDeque<>());
		stack.addLast(benchmarkId);
	}

	@Override
	public void reportBenchmark(UUID benchmarkId, String threadName) {
		Deque<UUID> stack = activeBenchmarks.get(threadName);
		if (!stack.getLast().equals(benchmarkId)) {
			throw new BenchmarkException(String.format("Current benchmark of thread %s is not %s", threadName, benchmarkId));
		}
		stack.removeLast();
		if (stack.isEmpty()) {
			activeBenchmarks.remove(threadName);
		}
	}

	@Override
	public Optional<UUID> getCurrentBenchmarkId(String threadName) {
		Deque<UUID> stack = activeBenchmarks.get(threadName);
		if (stack == null || stack.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(stack.peekLast());
	}
}
