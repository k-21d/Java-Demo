package com.k21d.kafka.kafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaDemoApplication.class, args);
        SpringKafkaProducer kafkaProducer = context.getBean(SpringKafkaProducer.class);
        for (int i=0;i<10;i++){
            kafkaProducer.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
