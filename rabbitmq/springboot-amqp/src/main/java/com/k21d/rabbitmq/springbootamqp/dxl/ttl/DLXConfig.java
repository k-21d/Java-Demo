package com.k21d.rabbitmq.springbootamqp.dxl.ttl;

import com.k21d.rabbitmq.springbootamqp.util.ResourceUtil;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class DLXConfig {

    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean("oriUseExchange")
    public DirectExchange exchange() {
        return new DirectExchange("ORI_USE_EXCHANGE", true, false, new HashMap<>());
    }

    @Bean("oriUserQueue")
    public Queue queue(){
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl", 10000); //10s后变成死信
        map.put("x-dead-letter-exchange","DEAD_LETTER_EXCHANGE");//队列中的消息变成死信后，进入死信交换机
        return new Queue("ORI_USE_QUEUE", true, false, false, map);
    }

    @Bean
    public Binding binding(@Qualifier("oriUserQueue") Queue queue, @Qualifier("oriUseExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("k21d.ori.use");
    }

    @Bean("deadLetterExchange")
    public TopicExchange deadLetterExchange(){
        return new TopicExchange("DEAD_LETTER_EXCHANGE",true, false, new HashMap<>());
    }

    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue("DEAD_LETTER_QUEUE", true, false, false,new HashMap<>());
    }
    @Bean
    public Binding bindingDead(@Qualifier("deadLetterQueue") Queue queue, @Qualifier("deadLetterExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#"); //无条件路由
    }

}
