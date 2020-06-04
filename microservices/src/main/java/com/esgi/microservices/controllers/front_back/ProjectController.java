package com.esgi.microservices.controllers.front_back;

import com.esgi.microservices.dto.ProjectDto;
import com.esgi.microservices.dto.factory.ProjectMaker;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    ProjectMaker projectMaker;

    /**
     * Normalement pas besoin de post
     */
    @PostMapping("/adds")
    public void addProject(@Valid @RequestBody List<ProjectDto> projects) throws ParseException {
        List<Project> projectList = new ArrayList<>();
        for (ProjectDto dto: projects) {
            projectList.add(new ProjectMaker().convertToEntity(dto));
        }
        projectService.addProjects(projectList);
    }

    /**
     * Normalement pas besoin de post
     */
    @PostMapping("/add")
    public ResponseEntity<Boolean> addProject(@Valid @RequestBody ProjectDto projectDto) throws ParseException {
        if(projectService.addProject( new ProjectMaker().convertToEntity(projectDto) ) == null ){
            return ResponseEntity.badRequest().body(false);
        };
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() throws ParseException {

        List<Project> projects = projectService.getProjects();
        List<ProjectDto> dtos = new ArrayList<>();
        for(int i = 0; i < projects.size(); i++){
            dtos.add(projectMaker.convertToDto(projects.get(i)));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Project> findProjectWithName(@RequestParam(name = "name",
            required=true) String name){
        return ResponseEntity.ok(projectService.getProjectName(name).get());
    }
}
