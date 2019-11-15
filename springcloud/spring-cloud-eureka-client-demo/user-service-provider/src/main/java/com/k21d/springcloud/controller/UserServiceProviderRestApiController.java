package com.k21d.springcloud.controller;

import com.k21d.springcloud.domain.User;
import com.k21d.springcloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * {@link UserService 用户服务}  提供方REST API {@link RestController}
 *
 */
@RestController
public class UserServiceProviderRestApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public User saveUser(@RequestBody User user){
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
