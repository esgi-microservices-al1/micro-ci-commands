package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
@EnableDiscoveryClient
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    //private final Producer producer;
    //private final Consumer consumer;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project addProject(@Valid @RequestBody Project project) {
        Project sendtoqueue = projectService.addProject(project);
        //producer.sendMessage(sendtoqueue);
        //consumer.recievedMessage(sendtoqueue);
        return sendtoqueue;
    }

    @GetMapping("/")
    public List<Project> findAll() {
        return projectService.getProject();
    }


    @GetMapping("/project")
    public Optional<Project> findByType(@RequestParam(required = false) String type) {
        return projectService.getProjectByProjectName(type);
    }
}
