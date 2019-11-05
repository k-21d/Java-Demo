package com.k21d.rabbitmq.springbootamqp.dxl.ttl;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.k21d.rabbitmq.springbootamqp.dxl.ttl")
public class DLXSender {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DLXSender.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        // 随队列的过期属性过期，单位ms
        rabbitTemplate.convertAndSend("ORI_USE_QUEUE", "k21d.ori.use", "测试死信消息");

    }
}
