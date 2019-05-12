package ru.nntu.yajb.service;

public interface SystemInfoService {
	String getCpu();

	int getAvailableCores();

	long getRam();

	long getSwap();

	long getAvailableMemory();
}
