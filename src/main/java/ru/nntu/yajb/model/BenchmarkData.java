package ru.nntu.yajb.model;

public class BenchmarkData {
    private MetaData metaData;
    // todo: @nullable
    private PayloadData payloadData;
    // todo: @nullable
    private ContextData contextData;

    public BenchmarkData() {
    }

    public BenchmarkData(MetaData metaData) {
        this.metaData = metaData;
    }

    public BenchmarkData(MetaData metaData, PayloadData payloadData, ContextData contextData) {
        this.metaData = metaData;
        this.payloadData = payloadData;
        this.contextData = contextData;
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

    public ContextData getContextData() {
        return contextData;
    }

    public void setContextData(ContextData contextData) {
        this.contextData = contextData;
    }

    @Override
    public String toString() {
        return "BenchmarkData{" +
                "metaData=" + metaData +
                ", payloadData=" + payloadData +
                ", contextData=" + contextData +
                '}';
    }
}
