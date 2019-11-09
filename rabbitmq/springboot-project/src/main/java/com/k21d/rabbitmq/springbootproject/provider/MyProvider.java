package com.k21d.rabbitmq.springbootproject.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyProvider {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(){
        // 发送4条消息

        amqpTemplate.convertAndSend("","FIRST_QUEUE","-------- a direct msg");

        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","shanghai.k21d.teacher","-------- a topic msg : shanghai.k21d.teacher");
        amqpTemplate.convertAndSend("TOPIC_EXCHANGE","changsha.k21d.student","-------- a topic msg : changsha.k21d.student");

        amqpTemplate.convertAndSend("FANOUT_EXCHANGE","","-------- a fanout msg");

    }

}