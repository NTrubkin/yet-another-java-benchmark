package ru.nntu.yajb.config;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import org.aspectj.lang.Aspects;
import ru.nntu.yajb.aspect.benchmark.BenchmarkAspect;
import ru.nntu.yajb.service.data.BenchmarkService;
import ru.nntu.yajb.service.data.SimpleBenchmarkService;
import ru.nntu.yajb.service.postman.Postman;
import ru.nntu.yajb.service.send.control.SendControlService;
import ru.nntu.yajb.util.BenchmarkException;

import java.io.IOException;
import java.util.Properties;

import static ru.nntu.yajb.config.ConstantProvider.POSTMAN_PROP;
import static ru.nntu.yajb.config.ConstantProvider.POSTMAN_VALUE;
import static ru.nntu.yajb.config.ConstantProvider.PROPERTIES_FILE;
import static ru.nntu.yajb.config.ConstantProvider.SEND_CONTROL_PROP;
import static ru.nntu.yajb.config.ConstantProvider.SEND_CONTROL_VALUE;


public class ConfigModule extends AbstractModule {
	@Override
	protected void configure() {
		Properties props = loadProperties();
		bind(BenchmarkService.class)
				.to(SimpleBenchmarkService.class)
				.in(Singleton.class);
		try {
			bind(SendControlService.class)
					.to(Class.forName(props.getProperty(SEND_CONTROL_PROP)).asSubclass(SendControlService.class));
			bind(Postman.class)
					.to(Class.forName(props.getProperty(POSTMAN_PROP)).asSubclass(Postman.class));
		} catch (ClassNotFoundException e) {
			// todo: specify message
			throw new BenchmarkException("cant find class", e);
		}

		requestInjection(Aspects.aspectOf(BenchmarkAspect.class));
	}

	private Properties loadProperties() {
		Properties props = getDefaultProperties();
		try {
			props.load(this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		} catch (IOException e) {
			// todo: log "can't open properties file, use defaults"
		}
		Names.bindProperties(binder(), props);
		return props;
	}

	private Properties getDefaultProperties() {
		Properties defaults = new Properties();
		defaults.setProperty(SEND_CONTROL_PROP, SEND_CONTROL_VALUE);
		defaults.setProperty(POSTMAN_PROP, POSTMAN_VALUE);
		return defaults;
	}
}
