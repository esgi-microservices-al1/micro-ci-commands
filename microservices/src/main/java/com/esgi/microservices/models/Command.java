package com.esgi.microservices.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@ApiModel(description = "Class representing a command in the application.")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "command")
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "Unique identifier of the Command.",
            example = "1000", required = true, position = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_generator")
    @SequenceGenerator(
            name = "command_generator",
            sequenceName = "command_sequence",
            initialValue = 1000
    )
    @NonNull
    private Long id;
    @ApiModelProperty(notes = "command of the Command.",
            example = "ls -l", required = true, position = 1)
    @NonNull
    private String command;
    @ApiModelProperty(notes = "stdout sort : of the Command.",
            example = "true", required = false, position = 2)
    @NonNull
    private boolean stdout;
    @ApiModelProperty(notes = "DateCrested Command.",
            example = "14/07/2020", required = false, position = 3)
    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private Date create_time;

}
