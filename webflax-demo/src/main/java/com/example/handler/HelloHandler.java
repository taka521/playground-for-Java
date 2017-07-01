package com.example.handler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@Component
public class HelloHandler {

    public RouterFunction<ServerResponse> routes() {
        return route(GET("/"), this::hello)
                .andRoute(GET("/stream"), this::stream);
    }

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().body(Flux.just("Hello", "World"), String.class);
    }

    public Mono<ServerResponse> stream(ServerRequest request) {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        Flux<Integer> flux = Flux.fromStream(stream);
        return ok().contentType(MediaType.APPLICATION_STREAM_JSON).body(flux, Integer.class);
    }
}
