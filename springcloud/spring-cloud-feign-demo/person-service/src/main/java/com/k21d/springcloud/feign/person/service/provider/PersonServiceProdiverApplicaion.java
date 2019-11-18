package com.k21d.springcloud.feign.person.service.provider;

import com.k21d.springcloud.feign.api.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * {@link PersonService} 提供者应用
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class PersonServiceProdiverApplicaion {
    public static void main(String[] args) {
        SpringApplication.run(PersonServiceProdiverApplicaion.class,args);
    }
}
