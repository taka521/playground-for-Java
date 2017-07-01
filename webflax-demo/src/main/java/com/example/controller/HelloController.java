package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class HelloController {

    @GetMapping("/")
    Flux<String> hello() {
        return Flux.just("Hello", "World");
    }

    @GetMapping("/stream")
    Flux<Map<String, Integer>> stream() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1); // 無限ストリーム
        return Flux.fromStream(stream.limit(10))
                .map(i -> Collections.singletonMap("value", i));
    }

    @GetMapping("/streamNoLimit")
    Flux<Map<String, Integer>> streamNoLimit(){
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(tuple -> Collections.singletonMap("value", tuple.getT1()));

        // Flux.interval(Duration)で指定した時間毎に処理が実行される。
        // で、それをFlux<Stream>とzipする。
        // 上記の例だと、毎秒１件ずつデータが返ってくる

    }
}
