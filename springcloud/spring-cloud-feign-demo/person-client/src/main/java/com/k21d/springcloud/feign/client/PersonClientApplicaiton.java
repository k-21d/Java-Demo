package com.k21d.springcloud.feign.client;

import com.k21d.springcloud.feign.api.service.PersonService;
import com.k21d.springcloud.feign.client.ribbon.FirstServerForeverRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Person 客户端应用程序
 * // @EnableEurekaClient
 */
@SpringBootApplication
@EnableFeignClients(clients = PersonService.class)
//@RibbonClient(value = "person-service", configuration = PersonClientApplicaiton.class)
@EnableHystrix
public class PersonClientApplicaiton {
    public static void main(String[] args) {
        SpringApplication.run(PersonClientApplicaiton.class, args);
    }

//    @Bean
//    public FirstServerForeverRule firstServerForeverRule(){
//        return new FirstServerForeverRule();
//    }

}
