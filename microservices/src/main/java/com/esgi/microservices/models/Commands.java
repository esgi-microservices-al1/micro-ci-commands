package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "commands")
public class Commands implements Serializable {
    private static final long serialVersionUID = 1L;

    public Commands(Project project) {
        this.project = project;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cmdid;
    @NonNull
    private String command;
    @NonNull
    private Date datecreated;

    @JoinColumn(name = "project_id",nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Project project;
}
