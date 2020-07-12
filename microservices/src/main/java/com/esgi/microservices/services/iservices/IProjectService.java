package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project addProject(final Project project);

    List<Project> getProject();

    Optional<Project> getProjectByProjectName(final String name);
}
