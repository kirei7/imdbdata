package com.vlad.imdbdata.basis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/")
    public String helloWorld() {
        LOGGER.debug("hello world executed");
        return "Hello world";
    }
}
