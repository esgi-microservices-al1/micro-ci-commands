package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Commands;

import java.util.List;

public interface ICommandService {

    Commands addCommands(Commands command);

    List<Commands> getAllCommands();

    List<Commands> getAllCommandsByProject_id(Long id);

    Commands getById(Long id);

    String DeleteCommands(Long id);

}
