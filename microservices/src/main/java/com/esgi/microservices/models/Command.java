package com.esgi.microservices.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @CreatedDate
    private LocalDate create_time;

}
