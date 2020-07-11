package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "command")
public class Command implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_generator")
    @SequenceGenerator(
            name = "command_generator",
            sequenceName = "command_sequence",
            initialValue = 1000
    )
    @NonNull
    private Long id;
    @NonNull
    private String command;
    @NonNull
    private boolean stdout;

    @NonNull
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @CreatedDate
    private Date create_time;


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Commands commands;


}
