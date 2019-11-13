package com.k21d.spring.springbootbeanvalidation.web.controller;

import com.k21d.spring.springbootbeanvalidation.domain.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping("/user/save")
    public User save(@Valid @RequestBody User user){

        //API 调用的方式
        Assert.hasText(user.getName(),"名称不能为空");
        //JVM 断言
        assert user.getId() <=10000;

        return user;
    }
}
