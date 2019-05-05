package ru.nntu.yajb.service.data;

import com.google.inject.Inject;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.config.ConstantProvider;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.Context;
import ru.nntu.yajb.model.DataPackage;
import ru.nntu.yajb.model.Meta;
import ru.nntu.yajb.service.ActiveBenchmarkService;
import ru.nntu.yajb.service.postman.Postman;
import ru.nntu.yajb.service.send.control.SendControlService;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class SimpleBenchmarkService implements BenchmarkService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleBenchmarkService.class);

	private final SendControlService sendControl;
	private final Postman postman;

	private final List<BenchmarkData> dataList = new ArrayList<>();
	private final DataPackage dataPackage;
	private final ActiveBenchmarkService activeBenchmarkService;


	@Inject
	public SimpleBenchmarkService(SendControlService sendControl, Postman postman, ActiveBenchmarkService activeBenchmarkService) {
		this.sendControl = sendControl;
		this.postman = postman;
		this.activeBenchmarkService = activeBenchmarkService;

		dataPackage = new DataPackage(collectContext(), dataList);
	}

	// todo: think about threads
	@Override
	public BenchmarkData initBenchmark() {
		BenchmarkData data = new BenchmarkData(new Meta(), null);
		String threadName = Thread.currentThread().getName();
		data.getMeta().setParentId(activeBenchmarkService.getCurrentBenchmarkId(threadName).orElse(null));
		activeBenchmarkService.initBenchmark(data.getMeta().getId(), threadName);
		return data;
	}

	// todo: think about threads
	@Override
	public void reportBenchmark(BenchmarkData data) {
		dataList.add(data);
		activeBenchmarkService.reportBenchmark(data.getMeta().getId(), Thread.currentThread().getName());

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
		Context context = new Context();
		context.setAppStartTime(System.nanoTime());
		context.setOsName(SystemUtils.OS_NAME);
		context.setOsVersion(SystemUtils.OS_VERSION);
		context.setOsArch(SystemUtils.OS_ARCH);
		context.setJavaVersion(SystemUtils.JAVA_VERSION);
		context.setJvmName(SystemUtils.JAVA_VM_NAME);
		context.setJvmVendor(SystemUtils.JAVA_VM_VENDOR);
		context.setJvmVersion(SystemUtils.JAVA_VM_VERSION);
		context.setJvmParams(getJvmParams());
		return context;
	}

	private String getJvmParams() {
		List<String> params = ManagementFactory.getRuntimeMXBean().getInputArguments();
		return String.join(ConstantProvider.JVM_PARAMS_DELIMITER, params);
	}
}
