package ru.nntu.yajb.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import java.lang.management.ManagementFactory;

public class OshiSystemInfoService implements SystemInfoService {
	private static final Logger LOG = LoggerFactory.getLogger(OshiSystemInfoService.class);

	private final CentralProcessor processor;
	private final GlobalMemory memory;

	public OshiSystemInfoService() {
		LOG.info("Initializing Oshi");
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		processor = hal.getProcessor();
		memory = hal.getMemory();

	}

	@Override
	public String getCpu() {
		return processor.toString();
	}

	@Override
	public int getAvailableCores() {
		return ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors();
	}

	@Override
	public long getRam() {
		return memory.getTotal();
	}

	@Override
	public long getSwap() {
		return memory.getSwapTotal();
	}

	@Override
	public long getAvailableMemory() {
		return Runtime.getRuntime().maxMemory();
	}
}
