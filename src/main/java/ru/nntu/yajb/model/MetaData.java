package ru.nntu.yajb.model;

import lombok.Data;

@Data
public class MetaData {
    private String className;
    private String methodName;
    private long methodStartTime;
    private long methodRunTime;
    private String threadName;
    private long threadRunTime;
}
