package ru.nntu.yajb.model;

public class BenchmarkData {
    private MetaData metaData;
    private ArgumentData argumentData;

    public BenchmarkData() {
    }

    public BenchmarkData(MetaData metaData, ArgumentData argumentData) {
        this.metaData = metaData;
        this.argumentData = argumentData;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public ArgumentData getArgumentData() {
        return argumentData;
    }

    public void setArgumentData(ArgumentData argumentData) {
        this.argumentData = argumentData;
    }
}
