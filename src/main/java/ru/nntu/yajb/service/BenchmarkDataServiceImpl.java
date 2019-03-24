package ru.nntu.yajb.service;

import ru.nntu.yajb.model.BenchmarkData;

public class BenchmarkDataServiceImpl implements BenchmarkDataService {
    @Override
    public void put(BenchmarkData data) {
        System.out.println("put data");
    }
}
