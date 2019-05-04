package ru.nntu.yajb.model;

import java.util.List;

public class DataPackage {
	private Context context;
	private List<BenchmarkData> data;

	public DataPackage() {
	}

	public DataPackage(Context context, List<BenchmarkData> data) {
		this.context = context;
		this.data = data;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public List<BenchmarkData> getData() {
		return data;
	}

	public void setData(List<BenchmarkData> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataPackage{" +
				"context=" + context +
				", data=" + data +
				'}';
	}
}
