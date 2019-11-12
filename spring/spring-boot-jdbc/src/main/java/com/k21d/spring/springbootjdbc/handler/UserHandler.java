package com.k21d.spring.springbootjdbc.handler;

import com.k21d.spring.springbootjdbc.domain.User;
import com.k21d.spring.springbootjdbc.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    private final UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        //在Spring Web MVC 中使用@RequesetBody
        //在Spring Web Flux 使用ServerRequest
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        //map相当于转换工作
        Mono<Boolean> map = userMono.map(userRepository::save);
        return ServerResponse.ok().body(map,Boolean.class);
    }
}
