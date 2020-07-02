package com.esgi.microservices.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consul")
public class ConsulResource {

    @GetMapping
    public String ResourceChecker() {
        return "Commands is running";
    }
}
