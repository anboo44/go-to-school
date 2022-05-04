package com.uet.gts.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {

    @Value("${spring.application.name}")
    private String instanceName;

    @Autowired
    private Environment environment;

    @GetMapping("")
    public String hello() {
        String port = environment.getProperty("local.server.port");
        var msg = String.format("%s:%s is starting", instanceName, port);
        System.out.printf("=========[ %s ]=========%n", msg);
        return msg;
    }
}

