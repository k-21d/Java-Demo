package com.k21d.springcloud.controller;

import com.k21d.springcloud.domain.User;
import com.k21d.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserRestApiController {
    @Autowired
    private UserService userService;


    @PostMapping("/user/save")
    public User saveUser(@RequestParam String name){
        User user = new User();
        user.setName(name);
        if (userService.save(user)){
            return user;
        }else {
            return null;
        }
    }

    @GetMapping("/user/findAll")
    public Collection<User> list(){
        return userService.findAll();
    }
}
