package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.ICommandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService implements ICommandService {
    private static final Logger log = LoggerFactory.getLogger(CommandService.class);

    private final CommandRepository commandRepository;
    private final ProjectRepository projectRepository;


    @Transactional
    @Override
    public Commands addCommands(final Commands commands) {
        Optional<Project> search = projectRepository.findById(commands.getProject().getProject_id());
        if (search.isPresent()) {
            commandRepository.save(commands);
            return commands;
        } else {
            throw new ResourceNotFoundException("Project introvable");
        }
    }

    @Transactional
    @Override
    public List<Commands> getAllCommands() {
        List<Commands> commands = new ArrayList<>();
        commandRepository.findAll().forEach(commands::add);
        return commands;
    }

    @Override
    public List<Commands> getAllCommandsByProject_id(Long id) {
        return commandRepository.listCommandsOfProject(id);
    }

    @Override
    public Commands getById(final Long id) {
        return commandRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public String DeleteCommands(final Long id) {
        return commandRepository.findById(id)
                .map(commands -> {
                    commandRepository.delete(commands);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("command not found with id " + id));
    }
}
