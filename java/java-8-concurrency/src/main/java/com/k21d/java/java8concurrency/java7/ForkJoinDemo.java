package com.k21d.java.java8concurrency.java7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Fork/Join示例
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        //并行：多核心参与
        //并发：一同参与
        //ForkJoin线程池 ForkJoinPool
        System.out.printf("当前CPU 处理器数：%d\n",Runtime.getRuntime().availableProcessors());
        System.out.printf("当前公用的ForkJoin线程池 并行数：%d\n",ForkJoinPool.getCommonPoolParallelism());
        //与ThreadPoolExecutor 类似
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.printf("[Thread: %s]Hello World....\n",Thread.currentThread().getName());
            }
        });
        forkJoinPool.shutdown();
    }
}
