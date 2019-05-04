package ru.nntu.yajb.model;

import java.util.List;
import java.util.UUID;

public class Meta {
	private UUID id;
	private UUID parentId;
	private String classType;
	private String methodName;
	private List<String> argumentTypes;
	private String returnType;
	private long methodStartTime;
	private long methodRunTime;
	private String threadName;
	private long threadRunTime;
	private boolean threwException;

	public Meta() {
		id = UUID.randomUUID();
	}

	public Meta(UUID parentId,
	            String classType,
	            String methodName,
	            List<String> argumentTypes,
	            String returnType, long methodStartTime,
	            long methodRunTime,
	            String threadName,
	            long threadRunTime,
	            boolean threwException) {
		id = UUID.randomUUID();
		this.parentId = parentId;
		this.classType = classType;
		this.methodName = methodName;
		this.argumentTypes = argumentTypes;
		this.returnType = returnType;
		this.methodStartTime = methodStartTime;
		this.methodRunTime = methodRunTime;
		this.threadName = threadName;
		this.threadRunTime = threadRunTime;
		this.threwException = threwException;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
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

	public boolean isThrewException() {
		return threwException;
	}

	public void setThrewException(boolean threwException) {
		this.threwException = threwException;
	}

	@Override
	public String toString() {
		return "Meta{" +
				"id=" + id +
				", parentId=" + parentId +
				", classType='" + classType + '\'' +
				", methodName='" + methodName + '\'' +
				", argumentTypes=" + argumentTypes +
				", returnType='" + returnType + '\'' +
				", methodStartTime=" + methodStartTime +
				", methodRunTime=" + methodRunTime +
				", threadName='" + threadName + '\'' +
				", threadRunTime=" + threadRunTime +
				", threwException=" + threwException +
				'}';
	}
}
