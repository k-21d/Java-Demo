package com.k21d.springcloud.springcloudhystrixclientdemo;

import rx.Observer;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

public class RxJavaDemo {
    public static void main(String[] args) {
        Random random = new Random();
        Single.just("Hello,World")
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("执行结束");
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("熔断保护！");
                    }
                    @Override
                    public void onNext(String s) {
                        int value = random.nextInt(500);

                        if (value>300) {
                            throw new RuntimeException("time out");
                        }
                        System.out.println("hello world() costs"+value+"ms");
                    }
                });
    }
}
