package com.esgi.microservices.dto.factory;

import com.esgi.microservices.dto.ProjectDto;
import com.esgi.microservices.models.Project;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class ProjectMaker implements Converter<Project,ProjectDto> {
    @Override
    public Project convertToEntity(ProjectDto projectDto) throws ParseException {
        Project project = new Project();
        if(projectDto.getId() != null){
            project.setId(projectDto.getId());
        }
        if(projectDto.getName() != null){
            project.setName(projectDto.getName());
        }
        if(projectDto.getDatecreated() != null){
            project.setDatecreated(projectDto.getDatecreated());
        }
        return project;
    }

    @Override
    public ProjectDto convertToDto(Project project) {
        ProjectDto dto = new ProjectDto();
        if(project.getId() != null){
            dto.setId(project.getId());
        }
        if(project.getName() != null){
            dto.setName(project.getName());
        }
        if(project.getDatecreated() != null){
            dto.setDatecreated(project.getDatecreated());
        }
        return dto;
    }
}
