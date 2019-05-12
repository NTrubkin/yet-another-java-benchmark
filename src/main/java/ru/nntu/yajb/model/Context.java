package ru.nntu.yajb.model;

import java.util.UUID;

// Данные окружения сессии
public class Context {
	private UUID id;
	private String appName;     // todo: implement this
	private long appStartTime;
	private boolean debugMode;
	private String javaVersion;
	private String jvmName;
	private String jvmVendor;
	private String jvmVersion;
	private String osName;
	private String osVersion;
	private String osArch;
	private String cpu;
	private int availableCores;
	private long ram;
	private long swap;
	private long availableMemory;
	private String jvmParams;
	private String sessionNotes;

	public Context() {
		id = UUID.randomUUID();
	}

	public Context(String appName, long appStartTime, boolean debugMode, String javaVersion, String jvmName, String jvmVendor, String jvmVersion, String osName, String osVersion, String osArch, String cpu, int availableCores, long ram, long swap, long availableMemory, String jvmParams, String sessionNotes) {
		id = UUID.randomUUID();

		this.appName = appName;
		this.appStartTime = appStartTime;
		this.debugMode = debugMode;
		this.javaVersion = javaVersion;
		this.jvmName = jvmName;
		this.jvmVendor = jvmVendor;
		this.jvmVersion = jvmVersion;
		this.osName = osName;
		this.osVersion = osVersion;
		this.osArch = osArch;
		this.cpu = cpu;
		this.availableCores = availableCores;
		this.ram = ram;
		this.swap = swap;
		this.availableMemory = availableMemory;
		this.jvmParams = jvmParams;
		this.sessionNotes = sessionNotes;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public long getAppStartTime() {
		return appStartTime;
	}

	public void setAppStartTime(long appStartTime) {
		this.appStartTime = appStartTime;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getJvmName() {
		return jvmName;
	}

	public void setJvmName(String jvmName) {
		this.jvmName = jvmName;
	}

	public String getJvmVendor() {
		return jvmVendor;
	}

	public void setJvmVendor(String jvmVendor) {
		this.jvmVendor = jvmVendor;
	}

	public String getJvmVersion() {
		return jvmVersion;
	}

	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getOsArch() {
		return osArch;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public int getAvailableCores() {
		return availableCores;
	}

	public void setAvailableCores(int availableCores) {
		this.availableCores = availableCores;
	}

	public long getRam() {
		return ram;
	}

	public void setRam(long ram) {
		this.ram = ram;
	}

	public long getSwap() {
		return swap;
	}

	public void setSwap(long swap) {
		this.swap = swap;
	}

	public long getAvailableMemory() {
		return availableMemory;
	}

	public void setAvailableMemory(long availableMemory) {
		this.availableMemory = availableMemory;
	}

	public String getJvmParams() {
		return jvmParams;
	}

	public void setJvmParams(String jvmParams) {
		this.jvmParams = jvmParams;
	}

	public String getSessionNotes() {
		return sessionNotes;
	}

	public void setSessionNotes(String sessionNotes) {
		this.sessionNotes = sessionNotes;
	}

	@Override
	public String toString() {
		return "Context{" +
				"id=" + id +
				", appName='" + appName + '\'' +
				", appStartTime=" + appStartTime +
				", debugMode=" + debugMode +
				", javaVersion='" + javaVersion + '\'' +
				", jvmName='" + jvmName + '\'' +
				", jvmVendor='" + jvmVendor + '\'' +
				", jvmVersion='" + jvmVersion + '\'' +
				", osName='" + osName + '\'' +
				", osVersion='" + osVersion + '\'' +
				", osArch='" + osArch + '\'' +
				", cpu='" + cpu + '\'' +
				", availableCores=" + availableCores +
				", ram=" + ram +
				", swap=" + swap +
				", availableMemory=" + availableMemory +
				", jvmParams='" + jvmParams + '\'' +
				", sessionNotes='" + sessionNotes + '\'' +
				'}';
	}
}
