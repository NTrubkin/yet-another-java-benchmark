package ru.nntu.yajb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;

public class BenchmarkDataServiceImpl implements BenchmarkDataService {
    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkDataServiceImpl.class);

    @Override
    public void put(BenchmarkData data) {
        LOG.info("put data");
    }
}
