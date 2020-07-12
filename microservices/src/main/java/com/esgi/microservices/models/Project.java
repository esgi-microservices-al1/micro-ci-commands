package com.esgi.microservices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "project_generator")
    @SequenceGenerator(
            name = "project_generator",
            sequenceName = "project_sequence",
            initialValue = 1000
    )
    private Long project_id;
    private String projectName;
    private String projectPath;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private Date created_time;
}
