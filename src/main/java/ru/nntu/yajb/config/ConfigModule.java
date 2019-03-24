package ru.nntu.yajb.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.aspectj.lang.Aspects;
import ru.nntu.yajb.aspect.benchmark.BenchmarkAspect;
import ru.nntu.yajb.service.BenchmarkDataLogger;
import ru.nntu.yajb.service.BenchmarkDataService;

public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(BenchmarkDataService.class)
                .to(BenchmarkDataLogger.class)
                .in(Singleton.class);

        requestInjection(Aspects.aspectOf(BenchmarkAspect.class));
    }
}
