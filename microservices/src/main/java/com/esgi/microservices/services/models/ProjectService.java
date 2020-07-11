package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public Project addProject(final Project project) {
        if (projectRepository.existsByProjectName(project.getProjectName())) {
            throw new ResourceNotFoundException("The project type already on the database exists");
        } else {
            return projectRepository.save(project);
        }
    }

    @Override
    public List<Project> getProject() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectByProjectName(final String type) {
        Optional<Project> project = projectRepository.findByProjectName(type);
        if (project.isEmpty()) throw new ResourceNotFoundException("Type Project Not found!");
        return project;
    }
}
