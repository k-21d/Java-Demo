package com.k21d.spring.springbootjdbc.controller;

import com.k21d.spring.springbootjdbc.domain.User;
import com.k21d.spring.springbootjdbc.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 用户RestController on WebMVC
 */
@RestController
public class UserController {

    private final UserRepository userRepository;

   // private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/web/mvc/user/save")
    public Boolean save(@RequestBody User user) throws ExecutionException, InterruptedException {
//        Future<Boolean> submit = executorService.submit(() -> {
//            return userRepository.save(user);
//        });
//        return submit.get();
        return userRepository.save(user);
    }



}
