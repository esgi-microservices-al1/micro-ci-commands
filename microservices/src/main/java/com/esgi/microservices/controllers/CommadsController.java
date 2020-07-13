package com.esgi.microservices.controllers;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.rabbitmq.Producer;
import com.esgi.microservices.services.models.CommandsService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/commands")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class CommadsController {
    private static final Logger log = LogManager.getLogger(CommadsController.class);

    private final CommandsService commandService;

    private final Producer producer;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCommmands(@RequestBody Commands commands) {
        log.info("< sendRequest bodyCommands:{}", commands.getCommands());
        if (commands.getProject().getProject_id() == null) {
            throw new ResourceNotFoundException("Your project_Id is not correct");
        } else {
            String commandsList = commandService.addCommands(commands);
            producer.sendMessage(commandsList);
            return commandsList;
        }
    }

    @GetMapping
    public List<Commands> getCommands() {
        return commandService.getAllCommands();
    }

    @GetMapping(value = "/filter")
    public List<Commands> getAllCommandsByProject_id(@RequestParam(required = false) Long id) {
        return commandService.getAllCommandsByProject_id(id);
    }

    @GetMapping(value = "/filter/{id}")
    public Commands getCommandsById(@PathVariable Long id) {
        return commandService.getCommandsById(id);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteCommands(@PathVariable Long id) {
        return commandService.deleteCommands(id);
    }
}

