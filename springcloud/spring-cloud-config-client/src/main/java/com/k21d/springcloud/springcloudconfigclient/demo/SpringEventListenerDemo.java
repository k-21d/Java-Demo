package com.k21d.springcloud.springcloudconfigclient.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring 自定义 事件/监听器
 */
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        //Annotation驱动的Spring上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册监听器


        context.addApplicationListener((ApplicationListener<MyApplicationEvent>) applicationEvent -> {
            //监听器得到事件
            System.out.println("接收到时间消息："+applicationEvent.getSource()+"at "+context);
        });
        //发布时间

        context.refresh();
        context.publishEvent(new MyApplicationEvent(context,"hello,world"));
        context.publishEvent(new MyApplicationEvent(context,1));
        context.publishEvent(new MyApplicationEvent(context,2));
        context.publishEvent(new MyApplicationEvent(context,3));
    }

    private static class MyApplicationEvent extends ApplicationEvent{
        private final ApplicationContext applicationContext;

        public MyApplicationEvent(ApplicationContext applicationContext, Object source) {
            super(source);
            this.applicationContext = applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }
}
