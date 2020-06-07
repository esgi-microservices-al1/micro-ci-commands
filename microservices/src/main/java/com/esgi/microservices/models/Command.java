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
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;

    public Command() { }
    public Command(Project project) {
        this.project = project;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long cmdid;
    @NonNull
    private String command;
    private boolean affichable; //ex: cd ... affichable, mvn install : pas affichable
    @NonNull
    @Column(unique = true)
    private String datecreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id",nullable=false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Project project;


}
