package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.services.models.CommandService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/command")
@RequiredArgsConstructor
public class CommadController {
    private static final Logger log = LogManager.getLogger(CommadController.class);

    private final CommandService commandService;


    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Command> getCommand() {
        return commandService.getAllCommand();
    }


    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Command getCommandById(@PathVariable Long id) {
        Command getCommand = commandService.getCommandById(id);
        return getCommand;
    }

    @DeleteMapping(value = "/commands/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void DeleteCommand(@PathVariable Long id) {
        commandService.DeleteCommand(id);
    }
}

