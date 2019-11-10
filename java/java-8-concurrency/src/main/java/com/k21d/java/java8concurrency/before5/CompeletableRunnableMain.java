package com.k21d.java.java8concurrency.before5;

/**
 * 可完成的的{@link Runnable}
 */
public class CompeletableRunnableMain {
    public static void main(String[] args) {
        CompetetableRunnable competetableRunnable = new CompetetableRunnable();
        Thread thread = new Thread(competetableRunnable,"Sub");

        thread.start();
        try {
            //等待线程执行结束，串行操作
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[Thread: %s]Starting....\n",Thread.currentThread().getName());
        System.out.println(competetableRunnable.compeleted);
    }



    private static class CompetetableRunnable implements Runnable{
        private  boolean compeleted;
        @Override
        public void run() {
            System.out.printf("[Thread: %s]Hello World....\n",Thread.currentThread().getName());
            compeleted = true;
        }

        public boolean isCompeleted() {
            return compeleted;
        }
    }
}
