package com.k21d.kafka.kafkademo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MyKafkaProducer extends Thread{
    //priducer api
    KafkaProducer<Integer,String> producer;
    String topic; //主题

    public MyKafkaProducer(String topic){
        //连接的字符串
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.3.25:9092,192.168.3.26:9092,192.168.3.34:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"k21d-producer");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.k21d.kafka.kafkademo.MyPartition");
        //异步需要设置
//        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,"");
//        properties.put(ProducerConfig.LINGER_MS_CONFIG,"");

        //序列化

        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<Integer, String>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int num = 0;
        while (num<20){
            try {
                String msg = "k21d kafka send msg:"+ num;
                //同步get()-> Future()
//                producer.send(new ProducerRecord<>(topic, msg), new Callback() {
//                    @Override
//                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//                        System.out.println(recordMetadata.offset()+"->"+ recordMetadata.partition()+"->"+recordMetadata.topic());
//                    }
//                });
                //get会去拿到发送的结果,阻塞
                RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(topic, msg)).get();
                System.out.println(recordMetadata.offset()+"->"+ recordMetadata.partition()+"->"+recordMetadata.topic());
                TimeUnit.SECONDS.sleep(2);
                ++num;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new MyKafkaProducer("test-partition").start();
    }
}
