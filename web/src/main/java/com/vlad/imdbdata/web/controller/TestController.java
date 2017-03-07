package com.vlad.imdbdata.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello world";
    }
}
