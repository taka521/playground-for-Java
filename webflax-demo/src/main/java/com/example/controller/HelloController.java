package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    Flux<Map<String, Integer>> streamNoLimit() {
        Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
        return Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1)))
                .map(tuple -> Collections.singletonMap("value", tuple.getT1()));

        // Flux.interval(Duration)で指定した時間毎に処理が実行される。
        // で、それをFlux<Stream>とzipする。
        // 上記の例だと、毎秒１件ずつデータが返ってくる

    }

    @PostMapping("/echo")
    Mono<String> echo(@RequestBody Mono<String> body) {
        return body.map(String::toUpperCase);

        /*
            @RequestBodyで、リクエストボディを受け取ることができる。
            で、それをMonoでラップすることによって非同期でチェインできる。
            (Monoでラップしなかった場合には、ノンブロッキングで同期な処理になる）
            Monoは0...1要素のPublisher。
         */
    }

    @PostMapping("/stream")
    Flux<Map<String, Integer>> stream(@RequestBody Flux<Map<String, Integer>> body){
        return body.map(m -> Collections.singletonMap("value", m.get("value") * 2));

        /*
            リクエストボディに複数の値が来る場合、Fluxでラップする。

            curl -v localhost:8080/stream -d '{"value":1}{"value":2}{"value":3}'
            -H 'Content-Type: application/stream+json'  -H 'Accept: text/event-stream'
         */
    }
}
