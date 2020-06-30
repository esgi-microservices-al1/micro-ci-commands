package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
<<<<<<< HEAD
    Project addProject(Project project);

    List<Project> getProject();

    Optional<Project> getProjectByProjectName(String name);
=======
    List<Project> addProjects(List<Project> projects);
    List<Project> getProjects();
    Optional<Project> getProjectName(String name);
    Project getProjectId(Long id);
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
}
