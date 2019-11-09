package com.k21d.rabbitmq.springbootamqp.basic;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.k21d.rabbitmq.springbootamqp.basic")
public class BasicSender {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BasicSender.class);
        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        rabbitTemplate.convertAndSend("","BASIC_FIRST_QUEUE","-------- a direct msg");

        rabbitTemplate.convertAndSend("BASIC_TOPIC_EXCHANGE","shanghai.k21d.teacher","-------- a topic msg : shanghai.k21d.teacher");
        rabbitTemplate.convertAndSend("BASIC_TOPIC_EXCHANGE","changsha.k21d.student","-------- a topic msg : changsha.k21d.student");

        rabbitTemplate.convertAndSend("BASIC_FANOUT_EXCHANGE","","-------- a fanout msg");


    }
}
