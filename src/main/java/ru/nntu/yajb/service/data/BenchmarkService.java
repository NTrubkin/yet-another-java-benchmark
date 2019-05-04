package ru.nntu.yajb.service.data;

import ru.nntu.yajb.model.BenchmarkData;

public interface BenchmarkService {
	BenchmarkData initBenchmark();

	void reportBenchmark(BenchmarkData data);

	void finishSession();
}
