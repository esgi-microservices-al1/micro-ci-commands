package com.esgi.microservices.servicestest;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectServiceTest {
    protected ProjectService projectService;

    @BeforeEach
    protected void setup() {
        projectService = mock(ProjectService.class);
    }

    @Test
    void should_project_empty_after_init() {
        given(projectService.getProject()).willReturn(new ArrayList<>());
        assertTrue(projectService.getProject().isEmpty());
    }

    @Test
    void should_return_all_projects() {
        ArrayList<Project> project = new ArrayList<>();
        project.add(new Project());
        project.add(new Project());
        project.add(new Project());

        given(projectService.getProject()).willReturn(project);
        assertFalse(project.isEmpty());
        assertEquals(3, projectService.getProject().size());
    }

    @Test
    void should_return_project_with_name_project() {
        Project project = new Project();
        given(projectService.getProjectByProjectName("test")).willReturn(Optional.ofNullable(project));
        assertNotNull(projectService.getProjectByProjectName("test"));
    }

    @Test
    void should_create_project_and_returns_project() {
        Project project = new Project();
        project.setProjectName("test");

        when(projectService.addProject(any())).thenReturn(project);
        assertEquals("test", project.getProjectName());
    }
}
