package com.k21d.rabbitmq.springbootamqp.basic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "BASIC_THIRD_QUEUE")
public class ThirdConsumer {
    @RabbitHandler
    public void process(String msg){
        System.out.println("third queue receive msg:"+ msg);
    }
}
