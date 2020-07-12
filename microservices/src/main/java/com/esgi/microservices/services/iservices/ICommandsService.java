package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Commands;

import java.util.List;

public interface ICommandsService {

    String addCommands(final Commands command);

    List<Commands> getAllCommands();

    List<Command> getAllCommand();

    List<Commands> getAllCommandsByProject_id(final Long id);

    Commands getCommandsById(final Long id);

    String deleteCommands(final Long id);

}
