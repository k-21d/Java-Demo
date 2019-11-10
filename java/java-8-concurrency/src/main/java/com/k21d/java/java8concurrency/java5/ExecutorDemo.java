package com.k21d.java.java8concurrency.java5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        //执行器、线程池(ThreadPoolExecutir)是它的一种实现
        //线程池是线程复用
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.printf("[Thread: %s]Hello World....\n",Thread.currentThread().getName());
            }
        });

        executor.shutdown();
    }
}
