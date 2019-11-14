package com.k21d.springcloud.springcloudconfigclient.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class EchoEnvController {
    @Value("${my.name}")
    private String myName;


//    private final Environment environment;
//
//    @Autowired
//    public EchoEnvController(Environment environment) {
//        this.environment = environment;
//    }
//    @GetMapping("/echo/env/{name}")
//    public String environment(@PathVariable String name){
//        return environment.getProperty(name);
//    }
    @GetMapping("/my-name")
    public String getMyName(){
        return myName;
    }
}
