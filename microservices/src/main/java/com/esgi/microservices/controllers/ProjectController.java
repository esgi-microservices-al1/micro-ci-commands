package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ReplayProcessor;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private static List<Project> messages = new ArrayList<>();
    private final ProjectService projectService;
    // STREAM de notifications
    private ReplayProcessor<Project> notifications = ReplayProcessor.create(0, false);

    //private final Producer producer;
    //private final Consumer consumer;

    @GetMapping(value = "/project/subscribe", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Project> notification() {
        return Flux.from(notifications);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project addProject(@Valid @RequestBody Project project) {
        Project sendtoqueue = projectService.addProject(project);
        //producer.sendMessage(sendtoqueue);
        //consumer.recievedMessage(sendtoqueue);
        notifications.onNext(sendtoqueue);
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
