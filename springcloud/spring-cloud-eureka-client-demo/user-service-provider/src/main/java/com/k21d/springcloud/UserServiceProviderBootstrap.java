package com.k21d.springcloud;

import com.k21d.springcloud.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * {@link UserService 用户服务} 引导类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceProviderBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderBootstrap.class,args);
    }
}
