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

public class BatchConfirmProducer {
    private final static String QUEUE_NAME = "ORIGIN_QUEUE";

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        //建立连接
        Connection connection = connectionFactory.newConnection();
        //创建消息通道
        Channel channel = connection.createChannel();

        String msg = "Hello, RabbitMQ , Batch Confirm";

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        try {
            channel.confirmSelect();
            for (int i=0;i<5;i++){
                //发送消息
                channel.basicPublish("",QUEUE_NAME,null, (msg+"-"+i).getBytes());
            }
            //批量确认结果，ACK如果是Multiple = True， 代表ACK里面的Delivery-Tag之前的消息都被确认了
            //比如5条消息可能只收到1个ACK，也可能收到2个
            //知道所有信息都发布，只要有一个未被Broker确认就会IOExceptiom
            channel.waitForConfirmsOrDie();
            System.out.println("消息发送完毕，批量确认成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        channel.close();
        connection.close();

    }
}
