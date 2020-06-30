package com.esgi.microservices.services.models;

<<<<<<< HEAD
import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.IProjectService;
import lombok.RequiredArgsConstructor;
=======
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.IProjectService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
<<<<<<< HEAD
    private final ProjectRepository projectRepository;


    @Override
    public Project addProject(final Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getProject() {
=======
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> addProjects(List<Project> projects) {
        LOGGER.info("Add Project: {}", projects);
        List<Project> addProjects = projectRepository.saveAll(projects);
        //kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(addProjects));
        return addProjects;
    }
    public Project addProject(Project project) {
        LOGGER.info("Add Project: {}", project);
        Project addProjects = projectRepository.save(project);
        //kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(addProjects));
        return addProjects;
    }

    @Override
    public List<Project> getProjects() {
        LOGGER.info("Find all commands");
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
        return projectRepository.findAll();
    }

    @Override
<<<<<<< HEAD
    public Optional<Project> getProjectByProjectName(final String type) {
        Optional<Project> project = projectRepository.findByProjectName(type);
        if (project.isEmpty()) throw new ResourceNotFoundException("Type Project Not found!");
=======
    public Optional<Project> getProjectName(String name) {
        Optional<Project> project = projectRepository.findByName(name);
        if (project.isEmpty()) throw new RuntimeException("Type Project Not found!");
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
        return project;
    }

    @Override
    public Project getProjectId(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type Project Not found!"));
        return project;
    }
}
