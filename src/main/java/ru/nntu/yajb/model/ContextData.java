package ru.nntu.yajb.model;

// Данные окружения сессии
public class ContextData {
    // todo: add guid
    private String appName;
    private long appStartTime;
    private boolean debugMode;
    private String jvm;
    private String jvmVersion;
    private String os;
    private String cpu;
    private String ram;

    public ContextData() {
    }

    public ContextData(String appName, long appStartTime, boolean debugMode, String jvm, String jvmVersion, String os, String cpu, String ram) {
        this.appName = appName;
        this.appStartTime = appStartTime;
        this.debugMode = debugMode;
        this.jvm = jvm;
        this.jvmVersion = jvmVersion;
        this.os = os;
        this.cpu = cpu;
        this.ram = ram;
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
}
