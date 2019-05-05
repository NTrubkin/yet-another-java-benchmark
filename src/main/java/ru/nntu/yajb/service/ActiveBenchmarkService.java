package ru.nntu.yajb.service;

import java.util.Optional;
import java.util.UUID;

public interface ActiveBenchmarkService {
	void initBenchmark(UUID benchmarkId, String threadName);

	void reportBenchmark(UUID benchmarkId, String threadName);

	Optional<UUID> getCurrentBenchmarkId(String threadName);
}
