package com.example.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class HelloHandler {

    /** ルーティング定義 */
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions.route(RequestPredicates.GET("/hello"), this::hello);
    }

    /** /hello へアクセスした場合の処理 */
    public Mono<ServerResponse> hello(final ServerRequest request) {

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("title", "Hello World");
        attributes.put("now", LocalDateTime.now());

        return ServerResponse.ok().contentType(MediaType.TEXT_HTML).render("hello", attributes);
    }

}
