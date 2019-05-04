package ru.nntu.yajb.service.data;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.ContextData;
import ru.nntu.yajb.model.DataPackage;
import ru.nntu.yajb.service.postman.Postman;
import ru.nntu.yajb.service.send.control.SendControlService;

import java.util.ArrayList;
import java.util.List;

public class SimpleBenchmarkDataService implements BenchmarkDataService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleBenchmarkDataService.class);

	private final SendControlService sendControl;
	private final Postman postman;

	private final List<BenchmarkData> dataList = new ArrayList<>();
	private final DataPackage dataPackage;

	@Inject
	public SimpleBenchmarkDataService(SendControlService sendControl, Postman postman) {
		this.sendControl = sendControl;
		this.postman = postman;

		dataPackage = new DataPackage(collectContext(), dataList);
	}

	@Override
	public void put(BenchmarkData data) {
		dataList.add(data);

		if (sendControl.sendPackageNow(data)) {
			postman.send(dataPackage);
			dataList.clear();
		}
	}

	private ContextData collectContext() {
		return new ContextData();
	}
}
