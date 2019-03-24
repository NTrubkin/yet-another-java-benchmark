package ru.nntu.yajb.model;


public class MetaData {
    private String className;
    private String methodName;
    private long methodStartTime;
    private long methodRunTime;
    private String threadName;
    private long threadRunTime;

    public MetaData() {
    }

    public MetaData(String className, String methodName, long methodStartTime, long methodRunTime, String threadName, long threadRunTime) {
        this.className = className;
        this.methodName = methodName;
        this.methodStartTime = methodStartTime;
        this.methodRunTime = methodRunTime;
        this.threadName = threadName;
        this.threadRunTime = threadRunTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getMethodStartTime() {
        return methodStartTime;
    }

    public void setMethodStartTime(long methodStartTime) {
        this.methodStartTime = methodStartTime;
    }

    public long getMethodRunTime() {
        return methodRunTime;
    }

    public void setMethodRunTime(long methodRunTime) {
        this.methodRunTime = methodRunTime;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public long getThreadRunTime() {
        return threadRunTime;
    }

    public void setThreadRunTime(long threadRunTime) {
        this.threadRunTime = threadRunTime;
    }
}
