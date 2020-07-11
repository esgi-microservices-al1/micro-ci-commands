package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commands")
public class Commands implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "commands_generator")
    @SequenceGenerator(
            name = "commands_generator",
            sequenceName = "commands_sequence",
            initialValue = 1000
    )
    private Long process_id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private List<Command> commands;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "project_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Project project;

    public Commands(Long process_id, List<Command> commandsList, Project project) {
        this.process_id = process_id;
        this.commands = commandsList;
        this.project = project;
    }

}
