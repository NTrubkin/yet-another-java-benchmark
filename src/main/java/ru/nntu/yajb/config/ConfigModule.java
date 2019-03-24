package ru.nntu.yajb.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.aspectj.lang.Aspects;
import ru.nntu.yajb.benchmark.BenchmarkAspect;
import ru.nntu.yajb.service.BenchmarkDataService;
import ru.nntu.yajb.service.BenchmarkDataServiceImpl;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BenchmarkDataService.class)
                .to(BenchmarkDataServiceImpl.class)
                .in(Singleton.class);

        requestInjection(Aspects.aspectOf(BenchmarkAspect.class));
    }
}
