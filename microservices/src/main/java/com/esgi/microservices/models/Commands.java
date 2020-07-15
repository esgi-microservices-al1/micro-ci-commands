package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@ApiModel(description = "Class representing a commands in the application.")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commands")
public class Commands implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Unique identifier of the Commands.",
            example = "1000", required = true, position = 0)
    @Id
    @GeneratedValue(generator = "commands_generator")
    @SequenceGenerator(
            name = "commands_generator",
            sequenceName = "commands_sequence",
            initialValue = 1000
    )
    private Long process_id;

    @ApiModelProperty(notes = "Array command of the Commands.",
            example = "comands[{command:ls,stdout:true,createdtime:12/07/2020}]", required = false, position = 1)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private List<Command> commands;

    @ApiModelProperty(notes = "project_ip of the Commands.",
            example = "project_id:1229", required = false, position = 2)
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
