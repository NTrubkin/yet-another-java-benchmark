package ru.nntu.yajb.service.data;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.Context;
import ru.nntu.yajb.model.DataPackage;
import ru.nntu.yajb.model.Meta;
import ru.nntu.yajb.service.postman.Postman;
import ru.nntu.yajb.service.send.control.SendControlService;

import java.util.ArrayList;
import java.util.List;

public class SimpleBenchmarkService implements BenchmarkService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleBenchmarkService.class);

	private final SendControlService sendControl;
	private final Postman postman;

	private final List<BenchmarkData> dataList = new ArrayList<>();
	private final DataPackage dataPackage;

	@Inject
	public SimpleBenchmarkService(SendControlService sendControl, Postman postman) {
		this.sendControl = sendControl;
		this.postman = postman;

		dataPackage = new DataPackage(collectContext(), dataList);
	}

	@Override
	public BenchmarkData initBenchmark() {
		return new BenchmarkData(new Meta(), null);
	}

	@Override
	public void reportBenchmark(BenchmarkData data) {
		dataList.add(data);

		if (sendControl.sendPackageNow(data)) {
			postman.send(dataPackage);
			dataList.clear();
		}
	}

	@Override
	public void finishSession() {
		// temporary do nothing
		// for batch send control services
	}

	private Context collectContext() {
		return new Context();
	}
}