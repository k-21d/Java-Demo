package com.k21d.rabbitmq.springbootamqp.ttl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.k21d.rabbitmq.springbootamqp.ttl")
public class TTLSender {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TTLSender.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setExpiration("4000"); // 消息的过期属性，单位ms
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        Message message = new Message("这条消息4s后过期".getBytes(), messageProperties);
        rabbitTemplate.send("TTL_EXCHANGE","k21d.ttl", message);

        //随队列的过期属性过期，单位ms
        rabbitTemplate.convertAndSend("TTL_EXCHANGE","k21d.ttl","这条消息");


    }
}
