package com.vlad.imdbdata.basis;

import com.vlad.imdbdata.basis.config.DomainConfig;
import com.vlad.imdbdata.basis.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan("com.vlad.imdbdata.basis.controller")
@Import({DomainConfig.class, WebConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
