package com.example.springcloudcontractdemo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
public class TestController {

    private final Clock clock;

    @GetMapping("/test")
    public String test() {
        log.info("Request received at /test endpoint");
        log.info("Time is: {}", LocalDateTime.now());
        log.info("Time is: {}", LocalDateTime.now(clock));
        return "Test successful!";
    }
}
