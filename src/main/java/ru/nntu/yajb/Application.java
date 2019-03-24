package ru.nntu.yajb;

import ru.nntu.yajb.benchmark.Benchmark;


public class Application {
    public static void main(String[] args) throws InterruptedException {
        Application app = new Application();
        app.test();
    }

    @Benchmark
    public void test() throws InterruptedException {
        Thread.sleep(500);
    }
}
