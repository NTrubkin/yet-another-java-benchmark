package ru.nntu.yajb.model;

// Данные одного измерения (бенчмарка)
public class BenchmarkData {
	private Meta meta;
	// todo: @nullable
	private Payload payload;

	public BenchmarkData() {
	}

	public BenchmarkData(Meta meta, Payload payload) {
		this.meta = meta;
		this.payload = payload;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "BenchmarkData{" +
				"meta=" + meta +
				", payload=" + payload +
				'}';
	}
}
