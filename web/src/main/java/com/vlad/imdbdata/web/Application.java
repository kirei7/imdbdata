package com.vlad.imdbdata.web;

import com.vlad.imdbdata.basis.config.DomainConfig;
import com.vlad.imdbdata.web.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@ComponentScan("com.vlad.imdbdata.web.controller")
@Import({DomainConfig.class, WebConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
