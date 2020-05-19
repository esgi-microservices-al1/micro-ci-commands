package com.esgi.microservices.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(generator = "User_generator")
//    @SequenceGenerator(
//            name = "User_generator",
//            sequenceName = "User_sequence",
//            initialValue = 1000
//    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long user_id;
    @NonNull
    private String FullName;
    @NonNull
    private String username;
    @NonNull
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY,orphanRemoval=true)
    @JsonBackReference("commands")
    private Set<Commands> commands;

}
