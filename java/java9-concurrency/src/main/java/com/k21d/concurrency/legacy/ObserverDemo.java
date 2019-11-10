package com.k21d.concurrency.legacy;


import java.util.Observable;

/**
 * 观察者模式
 */
public class ObserverDemo {
    public static void main(String[] args) {
        //第一个缺点：Vector作为底层存储（全线程安全的）
        //第二个缺点：没有实现泛型
        //第三个缺点：同步（阻塞）并且顺序倒序
        MyObservable observable = new MyObservable();
        observable.addObserver((ob,value)->{ //int
            System.out.printf("观察到的数值：%s\n",value);
        });
        observable.addObserver((ob,value)->{
            System.out.printf("第二个观察者-收到了：%s\n",value);
        });
        //Observable是倒序的
        observable.setChanged();
        //通知所有的观察者
        observable.notifyObservers(124124);
    }

    private static class MyObservable extends Observable {

        public void setChanged() {
            super.setChanged();
        }
    }
}
