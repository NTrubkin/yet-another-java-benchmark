package ru.nntu.yajb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nntu.yajb.model.BenchmarkData;

public class BenchmarkDataLogger implements BenchmarkDataService {
    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkDataLogger.class);

    @Override
    public void put(BenchmarkData data) {
        LOG.info(String.format("Put data %s", data));
    }
}
