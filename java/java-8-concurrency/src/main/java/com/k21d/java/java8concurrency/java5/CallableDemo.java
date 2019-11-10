package com.k21d.java.java8concurrency.java5;

import java.util.concurrent.*;

/**
 * Callable 是有返回值的操作，相对于Runnable
 * @see Runnable
 * @see Callable
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //执行器、线程池(ThreadPoolExecutir)是它的一种实现
        //线程池是线程复用
        ExecutorService executorService =
                Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "[Thread:]"+Thread.currentThread().getName()+"Hello World....";
            }
        });
        String result = future.get();
        System.out.println(result);
        executorService.shutdown();
    }
}
