package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;

import java.util.List;

public interface ICommandService {

    List<Command> getAllCommand();

    Command getCommandById(final Long id);

    String deleteCommand(final Long id);

}
