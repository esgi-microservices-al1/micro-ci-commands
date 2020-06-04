package com.esgi.microservices.services.models;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.IProjectService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
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
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectName(String name) {
        Optional<Project> project = projectRepository.findByName(name);
        if (project.isEmpty()) throw new RuntimeException("Type Project Not found!");
        return project;
    }
}
