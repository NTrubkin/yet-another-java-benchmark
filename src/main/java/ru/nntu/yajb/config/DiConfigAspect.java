package ru.nntu.yajb.config;

import com.google.inject.Guice;
import com.google.inject.Module;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class DiConfigAspect {
	private static final Logger LOG = LoggerFactory.getLogger(DiConfigAspect.class);

	@Before("execution(public static void main(String[]))")
	public void configure() {
		Module module = new ConfigModule();
		Guice.createInjector(module);
		LOG.debug("DI configured success");
	}
}
