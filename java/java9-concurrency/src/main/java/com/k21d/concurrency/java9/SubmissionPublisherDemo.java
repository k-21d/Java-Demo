package com.k21d.concurrency.java9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 *  {@link SubmissionPublisher}
 */
public class SubmissionPublisherDemo {

    public static void main(String[] args) throws InterruptedException {
        try(SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>()){
            publisher.subscribe(new IntegerSubcriber("A"));
            publisher.subscribe(new IntegerSubcriber("B"));
            publisher.subscribe(new IntegerSubcriber("C"));
            CompletableFuture<Void> consume = publisher.consume(value -> {
                System.out.printf("Thread[%s]: consumes value[%s] \n",
                        Thread.currentThread().getName(),
                        value);
            }).thenRunAsync(()->{
                System.out.printf("Thread[%s] thenRunAsync \n",Thread.currentThread().getName());
            });
            //提交数据到各个订阅器
            publisher.submit(100);
        }

        Thread.currentThread().join(500);

    }

    private static class IntegerSubcriber implements Flow.Subscriber<Integer>{

        private final String name;
        private Flow.Subscription subscription;

        private IntegerSubcriber(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.printf("Thread[%s]: Current Subscriber[%s] subscribes: subscription[%s] \n",
                    Thread.currentThread().getName(),
                    name,
                    subscription);
            this.subscription = subscription;
            subscription.request(1);

        }

        @Override
        public void onNext(Integer item) {
            System.out.printf("Thread[%s]: Current Subscriber[%s] receive: item[%d] \n",
                    Thread.currentThread().getName(),
                    name,
                    item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.err.println(throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.printf("Thread[%s]: Current Subscriber[%s] is completed \n",
                    Thread.currentThread().getName(),
                    name);
        }
    }
}
