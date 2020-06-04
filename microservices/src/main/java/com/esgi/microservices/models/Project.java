package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    private String name;
    private String datecreated;

    @OneToMany(fetch = FetchType.LAZY,orphanRemoval=true)
    @JsonBackReference("commands")
    private Set<Commands> commands;

}
