package ru.nntu.yajb.model;

// Данные одного измерения (бенчмарка)
public class BenchmarkData {
	// todo: add guid
	// todo: add parent
	private MetaData metaData;
	// todo: @nullable
	private PayloadData payloadData;

	public BenchmarkData() {
	}

	public BenchmarkData(MetaData metaData) {
		this.metaData = metaData;
	}

	public BenchmarkData(MetaData metaData, PayloadData payloadData) {
		this.metaData = metaData;
		this.payloadData = payloadData;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public PayloadData getPayloadData() {
		return payloadData;
	}

	public void setPayloadData(PayloadData payloadData) {
		this.payloadData = payloadData;
	}

	@Override
	public String toString() {
		return "BenchmarkData{" +
				"metaData=" + metaData +
				", payloadData=" + payloadData +
				'}';
	}
}
