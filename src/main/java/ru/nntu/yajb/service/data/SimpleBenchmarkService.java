package ru.nntu.yajb.service.data;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;
import ru.nntu.yajb.model.Context;
import ru.nntu.yajb.model.DataPackage;
import ru.nntu.yajb.model.Meta;
import ru.nntu.yajb.service.ActiveBenchmarkService;
import ru.nntu.yajb.service.SystemInfoService;
import ru.nntu.yajb.service.postman.Postman;
import ru.nntu.yajb.service.send.control.SendControlService;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

import static ru.nntu.yajb.config.ConstantProvider.JVM_DEBUG_ARG;
import static ru.nntu.yajb.config.ConstantProvider.JVM_PARAMS_DELIMITER;
import static ru.nntu.yajb.config.ConstantProvider.SESSION_NOTES_PROP;

public class SimpleBenchmarkService implements BenchmarkService {
	private static final Logger LOG = LoggerFactory.getLogger(SimpleBenchmarkService.class);

	private final SendControlService sendControl;
	private final Postman postman;

	private final List<BenchmarkData> dataList = new ArrayList<>();
	private final DataPackage dataPackage;
	private final ActiveBenchmarkService activeBenchmarkService;
	private final String sessionNotes;
	private final SystemInfoService systemInfo;
	private final RuntimeMXBean runtimeMxBean;

	@Inject
	public SimpleBenchmarkService(SendControlService sendControl,
	                              Postman postman,
	                              ActiveBenchmarkService activeBenchmarkService,
	                              @Named(SESSION_NOTES_PROP) String sessionNotes, SystemInfoService systemInfo) {
		this.sendControl = sendControl;
		this.postman = postman;
		this.activeBenchmarkService = activeBenchmarkService;
		this.sessionNotes = sessionNotes;
		this.systemInfo = systemInfo;

		runtimeMxBean = ManagementFactory.getRuntimeMXBean();
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
		context.setDebugMode(isDebugMode());

		context.setJavaVersion(SystemUtils.JAVA_VERSION);
		context.setJvmName(SystemUtils.JAVA_VM_NAME);
		context.setJvmVendor(SystemUtils.JAVA_VM_VENDOR);
		context.setJvmVersion(SystemUtils.JAVA_VM_VERSION);

		context.setOsName(SystemUtils.OS_NAME);
		context.setOsVersion(SystemUtils.OS_VERSION);
		context.setOsArch(SystemUtils.OS_ARCH);

		context.setCpu(systemInfo.getCpu());
		context.setAvailableCores(systemInfo.getAvailableCores());
		context.setRam(systemInfo.getRam());
		context.setSwap(systemInfo.getSwap());
		context.setAvailableMemory(systemInfo.getAvailableMemory());

		context.setJvmParams(getJvmParams());
		context.setSessionNotes(sessionNotes);
		return context;
	}

	private String getJvmParams() {
		return String.join(JVM_PARAMS_DELIMITER, runtimeMxBean.getInputArguments());
	}

	private boolean isDebugMode() {
		return runtimeMxBean.getInputArguments()
				.stream()
				.anyMatch(arg -> arg.contains(JVM_DEBUG_ARG));
	}
}
