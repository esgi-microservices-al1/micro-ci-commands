package com.esgi.microservices.controllers;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.rabbitmq.Producer;
import com.esgi.microservices.services.models.CommandsService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommadsController {
    private static final Logger log = LogManager.getLogger(CommadsController.class);

    private final CommandsService commandService;

    private final Producer producer;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCommmand(@RequestBody Commands commands) {
        log.info("< sendRequest bodyCommands:{}", commands.getCommands());
        if (commands.getProject().getProject_id() == null) {
            throw new ResourceNotFoundException("Your project_Id is not correct");
        } else {
            String commandsList = commandService.addCommands(commands);
            producer.sendMessage(commandsList);
            return commandsList;
        }
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commands> getCommands() {
        return commandService.getAllCommands();
    }

    @GetMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Commands> getAllCommandsByProject_id(@RequestParam(required = false) Long id) {
        return commandService.getAllCommandsByProject_id(id);
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Commands getCommandById(@PathVariable Long id) {
        Commands getCommands = commandService.getCommandsById(id);
        return getCommands;
    }

    @DeleteMapping(value = "/commands/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void DeleteCommands(@PathVariable Long id) {
        commandService.DeleteCommands(id);
    }
}

