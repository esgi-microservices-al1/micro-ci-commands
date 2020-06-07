package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    public Project() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String path;
    private String datecreated;

    @OneToMany(targetEntity = Command.class,cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonBackReference("commands")
    private List<Command> commands;

}
