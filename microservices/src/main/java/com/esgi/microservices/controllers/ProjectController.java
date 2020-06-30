package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping("/add")
    public void addProject(@Valid @RequestBody Project project) {
        projectService.addProject(project);
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
