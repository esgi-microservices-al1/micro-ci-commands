package com.esgi.microservices.services.iservices;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;

import java.util.List;

public interface ICommandService {
<<<<<<< HEAD

    Commands addCommands(Commands command);

    List<Commands> getAllCommands();

    Commands getById(Long id);

    String DeleteCommands(Long id);

=======
    Command addCommands(Command command, Project project);
    List<Command> getAllCommands();
    List<Command> getCommands(Long projectid);
    String DeleteCommands(Long Cmd_id);
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
}
