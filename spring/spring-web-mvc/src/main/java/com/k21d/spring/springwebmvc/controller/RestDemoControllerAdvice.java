package com.k21d.spring.springwebmvc.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestDemoControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public Object npe(Throwable throwable){
        return throwable.getMessage();
    }



    @ExceptionHandler(NoHandlerFoundException.class)
    public Object pageNotFound(HttpStatus httpStatus, HttpServletRequest request, Throwable throwable){
        Map<String, Object> errors = new HashMap<>();
        errors.put("statusCode",request.getAttribute("javax.servlet.error.status_code"));
        errors.put("requestUri",request.getAttribute("javax.servlet.error.request_uri  "));
        return errors;
    }
}
