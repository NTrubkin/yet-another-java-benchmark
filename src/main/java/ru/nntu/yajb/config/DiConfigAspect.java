package ru.nntu.yajb.config;

import com.google.inject.Guice;
import com.google.inject.Module;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DiConfigAspect {
    @Before("execution(public static void main(String[]))")
    public void method() {
        Module module = new ConfigModule();
        Guice.createInjector(module);
    }
}
