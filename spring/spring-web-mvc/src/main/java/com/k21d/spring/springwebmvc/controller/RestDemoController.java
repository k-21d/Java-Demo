package com.k21d.spring.springwebmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestDemoController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/npe")
    public void npe(){
        throw new NullPointerException("发生了一个异常");
    }

    @RequestMapping("/404.html")
    public Object handlerPageNotFind(HttpStatus httpStatus, HttpServletRequest request, Throwable throwable){
        Map<String, Object> errors = new HashMap<>();
        errors.put("statusCode",request.getAttribute("javax.servlet.error.status_code"));
        errors.put("requestUri",request.getAttribute("javax.servlet.error.request_uri  "));
        return errors;
    }
}
