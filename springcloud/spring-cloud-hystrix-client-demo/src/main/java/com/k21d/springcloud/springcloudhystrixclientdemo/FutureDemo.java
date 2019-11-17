package com.k21d.springcloud.springcloudhystrixclientdemo;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(() -> {
            int value = random.nextInt(200);
            System.out.println("hello world() costs"+value+"ms");
            Thread.sleep (value);
            return "hello world";
        });
        try {
            future.get(100, TimeUnit.MILLISECONDS);
        } catch (Exception  e) {
            System.err.printf("超时");
        }
        executorService.shutdown();
    }
}
