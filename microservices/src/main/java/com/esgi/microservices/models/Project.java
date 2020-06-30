package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
<<<<<<< HEAD
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
=======
import java.util.List;
import java.util.Set;


>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    public Project() {
    }

    @Id
<<<<<<< HEAD
    @GeneratedValue(generator = "project_generator")
    @SequenceGenerator(
            name = "project_generator",
            sequenceName = "project_sequence",
            initialValue = 1000
    )
    private Long project_id;
    private String projectName;
    private String projectPath;
    @CreatedDate
    private Date created_time;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonBackReference("commands")
    private Set<Commands> commands = new HashSet<>();
=======
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

>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
}
