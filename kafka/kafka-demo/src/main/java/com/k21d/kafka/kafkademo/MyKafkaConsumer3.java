package com.k21d.kafka.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class MyKafkaConsumer3 extends Thread{
    KafkaConsumer<Integer,String> consumer;
    String topic;

    public MyKafkaConsumer3(String topic){
        //连接的字符串
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.3.25:9092,192.168.3.26:9092,192.168.3.34:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"k21d-cunsumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"k21d-kid2");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"30000");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"1000");//自动提交(批量确认)
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //一个新的group的消费者去消费一个topic，就会从最早的消息开始消费
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");
        consumer = new KafkaConsumer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        consumer.subscribe(Collections.singleton(this.topic));
        while (true){
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach(record->{
                System.out.println("==================================================");
                System.out.println(record.key()+"->"+record.value()+"->"+record.offset());
                System.out.println("==================================================");
            });
        }
    }

    public static void main(String[] args) {
        new MyKafkaConsumer3("test-partition").start();
    }
}
