package com.esgi.microservices.controllers;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.rabbitmq.Producer;
import com.esgi.microservices.services.models.CommandsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(description = "Endpoints for AddCommands, GetByIdCommands,getAllCommandsByProjectId .GetAllCommand and Deleting of Commads.", tags = {"Commads"})
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/commands")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class CommadsController {
    private static final Logger log = LogManager.getLogger(CommadsController.class);

    private final CommandsService commandService;

    private final Producer producer;

    @ApiOperation(value = "Add a new list commads", tags = {"commads"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "commads created"),
            @ApiResponse(code = 400, message = "Invalid input"),})
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCommmands(@RequestBody @ApiParam("Commands to add. Cannot null or empty.") Commands commands) {
        log.info("< sendRequest bodyCommands:{}", commands.getCommands());
        if (commands.getProject().getProject_id() == null) {
            throw new ResourceNotFoundException("Your project_Id is not correct");
        } else {
            String commandsList = commandService.addCommands(commands);
            producer.sendMessage(commandsList);
            return commandsList;
        }
    }

    @ApiOperation(value = "Gell All Commands", tags = {"Commands"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping
    public List<Commands> getCommands() {
        return commandService.getAllCommands();
    }

    @ApiOperation(value = "Find commands by projectID", notes = "Returns list of commands", tags = {"commands"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Command.class),
            @ApiResponse(code = 404, message = "Commands not found")})
    @GetMapping(value = "/filter")
    public List<Commands> getAllCommandsByProject_id(@RequestParam(required = false) @ApiParam("project Id of the commands to be obtained. Cannot be empty.") Long id) {
        return commandService.getAllCommandsByProject_id(id);
    }

    @ApiOperation(value = "Find commands by ID", notes = "Returns a single commands", tags = {"commands"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Command.class),
            @ApiResponse(code = 404, message = "Commands not found")})
    @GetMapping(value = "/filter/{id}")
    public Commands getCommandsById(@PathVariable @ApiParam("Id of the commands to be obtained. Cannot be empty.") Long id) {
        return commandService.getCommandsById(id);
    }

    @ApiOperation(value = "Deletes a commands", tags = {"commands"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "commands not found")})
    @DeleteMapping(value = "/{id}")
    public String deleteCommands(@PathVariable @ApiParam("Id of the commands to be delete. Cannot be empty.") Long id) {
        return commandService.deleteCommands(id);
    }
}
