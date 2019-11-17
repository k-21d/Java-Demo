package com.k21d.springcloud;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 测试RestTemplate
 */
public class RestTemplateDemo {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//        Map<String,Object>  data = restTemplate.getForObject("http://localhost:8080/actuator/env", Map.class);
//        System.out.println(data);
        String dataStr = restTemplate.getForObject("http://localhost:8080/actuator/env", String.class);
        System.out.println(dataStr);
    }
}
