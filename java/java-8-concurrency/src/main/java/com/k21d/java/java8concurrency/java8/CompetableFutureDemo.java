package com.k21d.java.java8concurrency.java8;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * {@link java.util.concurrent.CompletableFuture} Demo
 * @see java.util.concurrent.CompletionStage
 */
public class CompetableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
        //1.完成操作,可以被其它线程去做
//        completableFuture.complete("hello, world");
//        String value = completableFuture.get();
//
//        System.out.println(value);
        //2.异步操作,阻塞操作
//        CompletableFuture asyncCompletableFuture = CompletableFuture.runAsync(()->{
//            System.out.println("Hello, World");
//        });
//        //仍然是阻塞操作
//        asyncCompletableFuture.get();
//
        //3.supplyAsync异步操作,阻塞操作
//        CompletableFuture<String> supplyAsynCompletableFuture = CompletableFuture.supplyAsync(()->{
//            //获取数据操作，假设来自于数据库
//            return String.format("[Thread:%s]:Hello World\n",Thread.currentThread().getName());
//        });
//        String value = supplyAsynCompletableFuture.get();
//        System.out.println("vaule= "+value);
//        System.out.println("Starting....");
        //4.合并操作
        CompletableFuture combinedCompletableFuture = CompletableFuture.supplyAsync(()->{
            //获取数据操作，假设来自于数据库
            return String.format("[Thread:%s]:Hello World\n",Thread.currentThread().getName());
        }).thenApply(value->{
            return value+" -来自于数据库";
        }).thenApply(value->{
            return value + " at " +LocalDateTime.now();
        }).thenApply(value->{
            System.out.println(value);
            return value;
        }).thenRun(()->{
            System.out.println("操作结束");
        });

        while (!combinedCompletableFuture.isDone()){

        }

        System.out.println("Starting....");
    }
}
