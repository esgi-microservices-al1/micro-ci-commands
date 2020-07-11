package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;

import java.util.List;

public interface ICommandService {

    List<Command> getAllCommand();

    Command getCommandById(Long id);

    String deleteCommand(Long id);

}
