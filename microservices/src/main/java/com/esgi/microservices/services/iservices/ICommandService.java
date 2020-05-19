package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Commands;

import java.util.List;

public interface ICommandService {
    Commands addCommands(Commands commands);
    List<Commands> getAllCommands();
    String DeleteCommands(Long Cmd_id);
}
