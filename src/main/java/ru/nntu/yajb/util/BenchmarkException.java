package ru.nntu.yajb.util;

public class BenchmarkException extends RuntimeException {
	public BenchmarkException(String message) {
		super(message);
	}

	public BenchmarkException(String message, Throwable cause) {
		super(message, cause);
	}
}
