package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(description = "Endpoints for addProject, findAll,findByNameProject. of Project.", tags = {"Project"})
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    //private final Producer producer;
    //private final Consumer consumer;


    @ApiOperation(value = "Add a new project", tags = {"project"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "forma JSON de creation de project"),
            @ApiResponse(code = 404, message = "Invalid input"),})
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project addProject(@Valid @RequestBody @ApiParam("Project to add. Cannot null or empty.") Project project) {
        Project sendtoqueue = projectService.addProject(project);
        //producer.sendMessage(sendtoqueue);
        //consumer.recievedMessage(sendtoqueue);
        return sendtoqueue;
    }


    @ApiOperation(value = "get all Project", tags = {"Project"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "JSON list Project", response = List.class),
            @ApiResponse(code = 404, message = "Found Output", response = List.class)})
    @GetMapping
    public List<Project> findAll() {
        return projectService.getProject();
    }


    @ApiOperation(value = "Find Project by projec name", notes = "Returns list of Project", tags = {"Project"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "object project JSON operation", response = Command.class),
            @ApiResponse(code = 404, message = "name project not found")})
    @GetMapping("/project")
    public Optional<Project> findByType(@RequestParam(required = false) String type) {
        return projectService.getProjectByProjectName(type);
    }
}
