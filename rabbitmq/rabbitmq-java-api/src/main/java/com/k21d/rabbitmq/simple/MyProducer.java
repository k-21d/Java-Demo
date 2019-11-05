package com.k21d.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyProducer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //连接IP
        connectionFactory.setHost("192.168.3.38");
        //连接端口
        connectionFactory.setPort(5672);
        //连接虚拟机
        connectionFactory.setVirtualHost("/");
        //用户
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        //建立连接
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        //发送消息
        String msg = "hello, rabbit mq";
        channel.basicPublish(EXCHANGE_NAME, "k21d.test", null, msg.getBytes());
        channel.close();
        connection.close();
    }
}
