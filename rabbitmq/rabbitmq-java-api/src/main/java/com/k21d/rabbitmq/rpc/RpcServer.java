package com.k21d.rabbitmq.rpc;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class RpcServer {
    private final static String REQUEST_QUEUE_NAME = "RPC_REQUEST";

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //创建一个新的连接 即TCP连接
        Connection connection = factory.newConnection();
        //创建一个通道
        final Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(REQUEST_QUEUE_NAME, true, false, false, null);
        //设置prefetch值 一次处理1条数据
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProperties = new AMQP.BasicProperties.Builder()
                        .correlationId(properties.getCorrelationId())
                        .build();
                //获取客户端指定的回调队列名
                String replyQueue = properties.getReplyTo();
                //返回获取消息的平方
                String message = new String(body, "UTF-8");
                // 计算平方
                Double mSquare = Math.pow(Integer.parseInt(message), 2);
                String repMsg = String.valueOf(mSquare);
                System.out.println(repMsg);
                // 把结果发送到回复队列
                channel.basicPublish("", replyQueue, replyProperties, repMsg.getBytes());
                //手动回应消息应答
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(REQUEST_QUEUE_NAME, true, consumer);

    }
}
