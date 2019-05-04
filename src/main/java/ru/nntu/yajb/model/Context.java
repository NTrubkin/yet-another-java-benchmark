package ru.nntu.yajb.model;

import java.util.UUID;

// Данные окружения сессии
public class Context {
	private UUID id;
	private String appName;
	private long appStartTime;
	private boolean debugMode;
	private String jvm;
	private String jvmVersion;
	private String os;
	private String cpu;
	private String ram;

	public Context() {
		id = UUID.randomUUID();
	}

	public Context(String appName, long appStartTime, boolean debugMode, String jvm, String jvmVersion, String os, String cpu, String ram) {
		id = UUID.randomUUID();
		this.appName = appName;
		this.appStartTime = appStartTime;
		this.debugMode = debugMode;
		this.jvm = jvm;
		this.jvmVersion = jvmVersion;
		this.os = os;
		this.cpu = cpu;
		this.ram = ram;
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

	public String getJvm() {
		return jvm;
	}

	public void setJvm(String jvm) {
		this.jvm = jvm;
	}

	public String getJvmVersion() {
		return jvmVersion;
	}

	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	@Override
	public String toString() {
		return "Context{" +
				"id=" + id +
				", appName='" + appName + '\'' +
				", appStartTime=" + appStartTime +
				", debugMode=" + debugMode +
				", jvm='" + jvm + '\'' +
				", jvmVersion='" + jvmVersion + '\'' +
				", os='" + os + '\'' +
				", cpu='" + cpu + '\'' +
				", ram='" + ram + '\'' +
				'}';
	}
}
