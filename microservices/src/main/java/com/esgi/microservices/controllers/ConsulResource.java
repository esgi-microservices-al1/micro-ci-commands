package com.esgi.microservices.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ConsulResource {

    @RequestMapping("/consul")
    public String ResourceChecker(){
        return "Commands is running";
    }
}
