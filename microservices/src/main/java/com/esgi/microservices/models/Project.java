package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long project_id;
    @NonNull
    private String type;
    @NonNull
    private Date created_time;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY,orphanRemoval=true)
    @JsonBackReference("commands")
    private Set<Commands> commands;
}
