package com.k21d.rabbitmq.springbootamqp.basic.consumer;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "BASIC_FIRST_QUEUE")
public class FirstConsumer {

    @RabbitHandler
    public void process(String msg, Channel channel, long deliveryTag) throws IOException {
        channel.basicAck(deliveryTag, true);
        System.out.println("first queue receive msg:"+msg);
    }
}
