package ru.nntu.yajb.service.send.control;

import ru.nntu.yajb.model.BenchmarkData;

public class AlwaysAllowSendControlService implements SendControlService {
	@Override
	public boolean sendPackageNow(BenchmarkData newData) {
		return true;
	}
}
