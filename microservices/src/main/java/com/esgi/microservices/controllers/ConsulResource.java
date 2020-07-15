package com.esgi.microservices.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(description = "Endpoints for check My API in consul of ConsulResource.", tags = {"ConsulResource"})
@RestController
@EnableDiscoveryClient
@RequestMapping("/api/v1/check")
public class ConsulResource {

    @ApiOperation(value = "Checkin My API", tags = {"ConsulResource"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Commands-ci is running", response = List.class),
            @ApiResponse(code = 404, message = "Found Output", response = List.class)})
    @GetMapping
    public String ResourceChecker() {
        return "Commands-ci is running";
    }
}
