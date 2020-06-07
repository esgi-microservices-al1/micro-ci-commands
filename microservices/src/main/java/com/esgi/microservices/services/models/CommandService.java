package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.ICommandService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandService implements ICommandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandService.class);
    private final CommandRepository commandRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public CommandService(CommandRepository commandRepository, ProjectRepository projectRepository) {
        this.commandRepository = commandRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    @Override
    public Command addCommands(Command command, Project project) {
        LOGGER.info("Add commands: {}", command);
        //Command addCmd = commandRepository.save(command);
        List<Command> commands= project.getCommands();
        commands.add(command);
        project.setCommands(commands);
        projectRepository.saveAndFlush(project);
        return commandRepository.findByProjectId(project.getId());
    }

    @Transactional
    @Override
    public List<Command> getAllCommands() {
         LOGGER.info("Find all commands");
        Iterable<Command> commands = commandRepository.findAll();
        return IteratorUtils.toList(commands.iterator());
    }

    @Transactional
    @Override
    public String DeleteCommands(Long Cmd_id) {
        return commandRepository.findById(Cmd_id)
                .map(commands -> {
                    commandRepository.delete(commands);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("commands not found with id " + Cmd_id));
    }
}
