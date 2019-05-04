package ru.nntu.yajb.service.send.control;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ru.nntu.yajb.model.BenchmarkData;

public class CounterSendControlService implements SendControlService {

	private static final int MIN_COUNT_TO_SEND = 1;
	private final int countToSend;
	private int counter = 0;

	@Inject
	public CounterSendControlService(@Named("yajb.CounterSendControlService.countToSend") int countToSend) {
		if (countToSend <= MIN_COUNT_TO_SEND) {
			throw new IllegalArgumentException();
		}

		this.countToSend = countToSend;
	}

	@Override
	public boolean sendPackageNow(BenchmarkData newData) {
		counter++;

		if (counter >= countToSend) {
			counter = 0;
			return true;
		}
		return false;
	}
}
