package com.esgi.microservices.dto;

import com.esgi.microservices.models.Project;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ProjectDto {
    private Long id;
    private String name;
    private String datecreated;
}
