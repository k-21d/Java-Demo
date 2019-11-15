package com.k21d.springcloud.springcloudeurekaserverdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEruekaServerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEruekaServerDemoApplication.class, args);
    }

}
