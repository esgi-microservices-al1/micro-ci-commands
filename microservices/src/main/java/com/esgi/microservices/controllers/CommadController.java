package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.services.models.CommandService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/command")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class CommadController {
    private static final Logger log = LogManager.getLogger(CommadController.class);

    private final CommandService commandService;


    @GetMapping
    public List<Command> getCommand() {
        return commandService.getAllCommand();
    }


    @GetMapping(value = "/filter/{id}")
    public Command getCommandById(@PathVariable Long id) {
        Command getCommand = commandService.getCommandById(id);
        return getCommand;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteCommand(@PathVariable Long id) {
        return commandService.deleteCommand(id);
    }
}

