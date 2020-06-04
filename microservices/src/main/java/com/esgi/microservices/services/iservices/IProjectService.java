package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    List<Project> addProjects(List<Project> projects);
    List<Project> getProjects();
    Optional<Project> getProjectName(String name);
}
