package com.pivotal.micrometerprometheus;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class HelloController {

    @Timed(
            value = "hello.satya.request",
            histogram = true,
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello")
    public String hello() {
        return "Hello ";
    }

    @Timed(
            value = "hello2.request",
            histogram = true,

            extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello2")
    public String hello2() {
        return "Hello ";
    }

}
