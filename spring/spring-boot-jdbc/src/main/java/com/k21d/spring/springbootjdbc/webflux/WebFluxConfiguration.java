package com.k21d.spring.springbootjdbc.webflux;

import com.k21d.spring.springbootjdbc.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class WebFluxConfiguration {
    @Bean
    public RouterFunction<ServerResponse> saveUser(UserHandler userHandler){
        RouterFunction<ServerResponse> build = RouterFunctions.route().POST("/web/flux/user/save", userHandler::save).build();
        return build;

    }
}
