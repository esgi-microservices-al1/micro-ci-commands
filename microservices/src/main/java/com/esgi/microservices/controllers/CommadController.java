package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.services.models.CommandService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Endpoints for GetByCommand, GetAllCommand and Deleting of Commad.", tags = {"Commad"})
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/command")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class CommadController {
    private static final Logger log = LogManager.getLogger(CommadController.class);

    private final CommandService commandService;


    @ApiOperation(value = "Gell All Command", tags = {"Command"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping
    public List<Command> getCommand() {
        return commandService.getAllCommand();
    }


    @ApiOperation(value = "Find command by ID", notes = "Returns a single command", tags = {"command"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Command.class),
            @ApiResponse(code = 404, message = "Command not found")})
    @GetMapping(value = "/filter/{id}")
    public Command getCommandById(@PathVariable @ApiParam("Id of the command to be obtained. Cannot be empty.") Long id) {
        Command getCommand = commandService.getCommandById(id);
        return getCommand;
    }


    @ApiOperation(value = "Deletes a command", tags = {"command"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 404, message = "Command not found")})
    @DeleteMapping(value = "/{id}")
    public String deleteCommand(@PathVariable @ApiParam("Id of the command to be delete. Cannot be empty.") Long id) {
        return commandService.deleteCommand(id);
    }
}

