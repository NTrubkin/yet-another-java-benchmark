package ru.nntu.yajb.model;


public class ContextData {
    private String appName;
    private long appStartTime;
    private boolean debugMode;

    public ContextData() {
    }

    public ContextData(String appName, long appStartTime, boolean debugMode) {
        this.appName = appName;
        this.appStartTime = appStartTime;
        this.debugMode = debugMode;
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
}
