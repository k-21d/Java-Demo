package com.k21d.rabbitmq.ttl;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class TTLProducer {
    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));
        //建立连接
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        String msg = "Hello Rabbit MQ, DLX MSG";

        //痛殴对立属性设置消息过期时间
        Map<String,Object> argsMap = new HashMap<>();
        argsMap.put("x-message-ttl",6000);

        //声明队列（默认交换机AMQP default， Direct）
        channel.queueDeclare("TEST_TTL_QUEUE",false,false,false,argsMap);

        //对每条消息设置过期时间
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)    //持久化消息
                .contentEncoding("UTF-8")
                .expiration("10000") //ttl
                .build();
        //此处联众方式设置消息过期时间的方式都使用了，将以较小都数值为准

        //发送消息
        channel.basicPublish("","TEST_DLX_QUEUE", properties, msg.getBytes());

        channel.close();
        connection.close();
    }
}
