package com.esgi.microservices.controllers;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@RequestMapping("/api/v1/check")
public class ConsulResource {

    @GetMapping(value ="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String ResourceChecker() {
        return "Commands-ci is running";
    }
}
