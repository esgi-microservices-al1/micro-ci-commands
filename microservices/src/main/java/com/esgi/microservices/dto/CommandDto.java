package com.esgi.microservices.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CommandDto {
    private Long cmdid;
    private String command;
    private boolean affichable; //ex: cd ... affichable, mvn install : pas affichable
    private String datecreated;
    private Long projectid;
}
