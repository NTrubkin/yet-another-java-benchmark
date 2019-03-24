package ru.nntu.yajb.model;


import java.util.List;

public class MetaData {
    private String classType;
    private String methodName;
    private List<String> argumentTypes;
    private String returnType;
    private long methodStartTime;
    private long methodRunTime;
    private String threadName;
    private long threadRunTime;

    public MetaData() {
    }

    public MetaData(String classType, String methodName, List<String> argumentTypes, String returnType, long methodStartTime, long methodRunTime, String threadName, long threadRunTime) {
        this.classType = classType;
        this.methodName = methodName;
        this.argumentTypes = argumentTypes;
        this.returnType = returnType;
        this.methodStartTime = methodStartTime;
        this.methodRunTime = methodRunTime;
        this.threadName = threadName;
        this.threadRunTime = threadRunTime;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getArgumentTypes() {
        return argumentTypes;
    }

    public void setArgumentTypes(List<String> argumentTypes) {
        this.argumentTypes = argumentTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
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

    @Override
    public String toString() {
        return "MetaData{" +
                "classType='" + classType + '\'' +
                ", methodName='" + methodName + '\'' +
                ", argumentTypes=" + argumentTypes +
                ", returnType='" + returnType + '\'' +
                ", methodStartTime=" + methodStartTime +
                ", methodRunTime=" + methodRunTime +
                ", threadName='" + threadName + '\'' +
                ", threadRunTime=" + threadRunTime +
                '}';
    }
}
