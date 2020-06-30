package com.esgi.microservices.dto;

import com.esgi.microservices.models.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
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
    @JsonRawValue
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String path;
    private String datecreated;
}
