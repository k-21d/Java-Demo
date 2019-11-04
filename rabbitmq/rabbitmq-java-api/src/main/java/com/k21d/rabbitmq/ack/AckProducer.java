package com.k21d.rabbitmq.ack;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class AckProducer {
    private final static String QUEUE_NAME = "TEST_ACK_QUEUE";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //建立连接
        Connection  connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        String msg = "ack message:拒收";
        //声明队列（默认交换机 AMQP default Direct）
        channel.queueDeclare(QUEUE_NAME, false, false, false,null);

        //发送消息
        for (int i = 0; i < 5; i++){
            channel.basicPublish("",QUEUE_NAME, null, (msg+i).getBytes());
        }
        channel.close();
        connection.close();
    }
}
