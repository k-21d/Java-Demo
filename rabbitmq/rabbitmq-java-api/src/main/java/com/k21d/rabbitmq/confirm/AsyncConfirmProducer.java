package com.k21d.rabbitmq.confirm;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class AsyncConfirmProducer {
    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //建立连接
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        String msg = "Hello, RabbitMQ , AsyncConfirm";

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //用来维护未确认消息的deliveryTag
        final SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        //这里不会打印所有响应的ACK，ACK可能有多个，有可能一次确认多条，也有可能一次确认一条
        //异步监听确认和未确认的消息
        //如果要重复运行，先停掉之前的生产者，清空队列
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("Broker未确认消息，标识: " + l);
                if (b) {
                    //headSet表示后面参数之前的所有元素，全部删除
                    confirmSet.headSet(l + 1L).clear();
                } else {
                    confirmSet.remove(l);
                }
                //TODO 这里添加重发的方法
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                //如果true表示批量执行了deliveryTag这个值以前（小于deliveryTag的）的所有消息，如果未false，表示单挑确认
                System.out.println(String.format("Broker已确认消息，标识： %d", l, b));
                if (b) {
                    //headset表示后面参数之前的所有元素，全部删除
                    confirmSet.headSet(l + 1L).clear();
                } else {
                    confirmSet.remove(l);
                }
                System.out.println("未确认的消息：" + confirmSet);
            }
        });
        //开启发送方确认模式
        channel.confirmSelect();
        for (int i = 0; i < 10; i++) {
            long nextSqqNo = channel.getNextPublishSeqNo();
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, (msg + "-" + i).getBytes());
            confirmSet.add(nextSqqNo);
        }
        System.out.println("所有消息：" + confirmSet);

    }
}
