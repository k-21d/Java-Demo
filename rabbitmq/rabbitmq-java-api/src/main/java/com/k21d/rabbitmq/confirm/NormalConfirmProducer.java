package com.k21d.rabbitmq.confirm;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class NormalConfirmProducer {

    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, InterruptedException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //建立连接
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        String msg = "Hello, RabbitMQ , Normal Confirm";
        //声明消息队列（默认交换机AMQP default， Direct）
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //开启发送方确认模式
        channel.confirmSelect();

        channel.basicPublish("",QUEUE_NAME, null, msg.getBytes());
        //普通Confirm，发送一条，确认一条
        if (channel.waitForConfirms()){
            System.out.println("消息发送成功");
        }
        channel.close();
        connection.close();
    }
}
