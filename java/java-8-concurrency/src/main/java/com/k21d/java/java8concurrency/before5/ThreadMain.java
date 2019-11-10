package com.k21d.java.java8concurrency.before5;

/**
 * Java Thread/Runnable编程模型（Java 5之前）
 */
public class ThreadMain {
    public static void main(String[] args) {

        //这个结果不确定，和操作系统调度有关
        Thread thread = new Thread( new Runnable() {
            /**
             * synchronized是编程语言的修饰符号
             */
            @Override
            public void run() {
                System.out.printf("[Thread: %s]Hello World....\n",Thread.currentThread().getName());
            }
        },"Sub");
        thread.start();
        System.out.printf("[Thread: %s]Starting....\n",Thread.currentThread().getName());
    }
}
