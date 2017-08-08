package com.example;

import com.example.handler.HelloHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WebfluxThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxThymeleafDemoApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routes(HelloHandler handler) {
        return handler.routes();
    }
}
