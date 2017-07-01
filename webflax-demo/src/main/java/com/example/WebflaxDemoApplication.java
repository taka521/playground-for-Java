package com.example;

import com.example.handler.HelloHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

@SpringBootApplication
public class WebflaxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebflaxDemoApplication.class, args);
	}

	@Bean
	RouterFunction<ServerResponse> routes(HelloHandler handler){
		return handler.routes();
	}
}
