package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Commands;

import java.util.List;

public interface ICommandsService {

    String addCommands(Commands command);

    List<Commands> getAllCommands();

    List<Command> getAllCommand();

    List<Commands> getAllCommandsByProject_id(Long id);

    Commands getCommandsById(Long id);

    String DeleteCommands(Long id);

}
