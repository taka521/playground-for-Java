package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Controller
public class HelloController {

    @GetMapping(path = "/wild")
    public Mono<String> wild(final Model model){

        model.addAttribute("title", "Get Wild!!");
        model.addAttribute("now", LocalDateTime.now());
        return Mono.just("hello");
    }
}
