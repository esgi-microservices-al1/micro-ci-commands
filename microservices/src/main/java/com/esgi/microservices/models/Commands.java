package com.esgi.microservices.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commands")
public class Commands implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cmd_id;
    @NonNull
    private String command;
    @NonNull
    private Date created_time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User users ;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Project project;
}
