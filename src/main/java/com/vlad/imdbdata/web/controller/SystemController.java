package com.vlad.imdbdata.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Seems like manual stopping of the application may cause some errors
* related to partial saving of data in DB by Batch
* So here is controller which allows to stop an app
* */
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/shutdown")
    public void shutdownServer() {
        SpringApplication.exit(applicationContext);
    }
}
