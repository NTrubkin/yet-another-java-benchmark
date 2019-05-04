package ru.nntu.yajb.service.send.control;

import ru.nntu.yajb.model.BenchmarkData;

public interface SendControlService {
	boolean sendPackageNow(BenchmarkData newData);
}
