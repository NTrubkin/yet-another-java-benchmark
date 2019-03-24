package ru.nntu.yajb.model;

import lombok.Data;

@Data
public class ContextData {
    private String appName;
    private long appStartTime;
    private boolean debugMode;
}
