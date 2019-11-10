package com.k21d.java.java8concurrency.java5;

import java.util.concurrent.*;

public class FutureDemo {
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
        //等待完成
        while (true){
           if (future.isDone()){
               break;
           }
        }
        //Future#get()方法会阻塞当前线程
        String result = future.get();
        System.out.println(result);
        executorService.shutdown();
    }
}
