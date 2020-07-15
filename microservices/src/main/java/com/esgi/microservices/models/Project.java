package com.esgi.microservices.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Class representing a projects in the application.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Unique identifier of the Commands.",
            example = "1000", required = true, position = 0)
    @Id
    @GeneratedValue(generator = "project_generator")
    @SequenceGenerator(
            name = "project_generator",
            sequenceName = "project_sequence",
            initialValue = 1000
    )
    private Long project_id;
    @ApiModelProperty(notes = "ProjectName of the Project.",
            example = "python", required = false, position = 1)
    private String projectName;
    @ApiModelProperty(notes = "ProjectPath of the Project.",
            example = "python/home/python", required = false, position = 2)
    private String projectPath;

    @ApiModelProperty(notes = "created date of the Project.",
            example = "02/09/2008", required = false, position = 2)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private Date created_time;
}
