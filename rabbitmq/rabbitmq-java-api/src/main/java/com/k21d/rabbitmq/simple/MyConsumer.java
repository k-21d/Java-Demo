package com.k21d.rabbitmq.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConsumer {
    private final static String EXCHANGE_NAME = "SIMPLE_EXCHANGE";
    private final static String QUEUE_NAME = "SIMPLE_QUEUE";

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

        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);

        //声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        System.out.println("waiting for message...");

        //绑定队列和交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"k21d.test");

        //创建消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("Received message :"+msg);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("deliveryTag: "+envelope.getDeliveryTag());
            }
        };
        //开始获取消息
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
