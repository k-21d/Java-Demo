package com.k21d.springcloud.springcloudconfigclient.demo;

import java.util.*;

/**
 * 观察者模式数据变化是被动的
 */
public class ObserverDemo {
    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        //增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        });
        observable.setChanged();
        //发布者通知，订阅者是被动感知的（推模式）
        observable.notifyObservers("hello,world");
        echoIterator();
    }

    private static void echoIterator(){
        List<Integer> values = Arrays.asList(1,2,3,4,5,6);
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()){ //通过循环主动获取
            Integer next = iterator.next();
            System.out.println(next);
        }
    }

    public static class MyObservable extends Observable{
        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}
