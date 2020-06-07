package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;

import java.util.List;

public interface ICommandService {
    Command addCommands(Command command, Project project);
    List<Command> getAllCommands();
    List<Command> getCommands(Long projectid);
    String DeleteCommands(Long Cmd_id);
}
