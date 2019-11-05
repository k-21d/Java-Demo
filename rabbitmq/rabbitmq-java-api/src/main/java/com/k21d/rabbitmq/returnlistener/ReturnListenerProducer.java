package com.k21d.rabbitmq.returnlistener;

import com.k21d.rabbitmq.util.ResourceUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 当消息无法匹配到队列时，会发回给生产者
 */
public class ReturnListenerProducer {
    public static void main(String[] args) throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(ResourceUtil.getKey("rabbitmq.uri"));

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2,
                                     AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("=========监听器收到了无法路由，被返回的消息============");
                System.out.println("replyText:" + s);
                System.out.println("exchange:" + s1);
                System.out.println("routingKey:" + s2);
                System.out.println("message:" + new String(bytes));
            }
        });
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().deliveryMode(2).
                contentEncoding("UTF-8").build();
        // 在声明交换机的时候指定备份交换机
        //Map<String,Object> arguments = new HashMap<String,Object>();
        //arguments.put("alternate-exchange","ALTERNATE_EXCHANGE");
        //channel.exchangeDeclare("TEST_EXCHANGE","topic", false, false, false, arguments);

        // 发送到了默认的交换机上，由于没有任何队列使用这个关键字跟交换机绑定，所以会被退回
        // 第三个参数是设置的mandatory，如果mandatory是false，消息也会被直接丢弃
        channel.basicPublish("", "myDirect", true, properties, "return msg".getBytes());

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        channel.close();
        connection.close();
    }
}
