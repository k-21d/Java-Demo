package com.k21d.springcloud.springcloudconfigclient;

import com.k21d.springcloud.springcloudconfigclient.health.MyHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class SpringCloudConfigClientApplication {
    private final ContextRefresher contextRefresher;
    private final Environment environment;

    @Bean
    public MyHealthIndicator myHealthIndicator(){
        return new MyHealthIndicator();
    }

    @Autowired
    public SpringCloudConfigClientApplication(ContextRefresher contextRefresher, Environment environment) {
        this.contextRefresher = contextRefresher;
        this.environment = environment;
    }

    @Scheduled(fixedRate = 5*1000, initialDelay = 1000)
    public void autoRefresh(){
        Set<String> updatedPropertyNames =  contextRefresher.refresh();
        updatedPropertyNames.forEach(property->{
            System.out.printf("Thread[%s] 当前配置项已更新：具体key:%s ,具体value:%s \n",
                    Thread.currentThread().getName(),
                    property,
                    environment.getProperty(property));
        });
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigClientApplication.class, args);
    }
//
//    @Configuration
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public static class MyPropertySourceLocator implements PropertySourceLocator{
//
//        @Override
//        public PropertySource<?> locate(Environment environment) {
//            Map<String,Object> source = new HashMap<>();
//            source.put("server.port",9999);
//            MapPropertySource propertySource =
//                    new MapPropertySource("my-property-source",source);
//
//            return propertySource;
//        }
//    }
}
