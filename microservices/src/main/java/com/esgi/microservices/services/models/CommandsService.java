package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.repository.CommandsRepository;
import com.esgi.microservices.services.iservices.ICommandsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandsService implements ICommandsService {
    private static final Logger log = LoggerFactory.getLogger(CommandsService.class);

    private final CommandRepository commandRepository;
    private final CommandsRepository commandsRepository;


    @Transactional
    @Override
    public String addCommands(final Commands commands) {
        commandsRepository.save(commands);
        List<Command> commandsList = new ArrayList<>(commands.getCommands());
        Commands commands1 = new Commands(commands.getProcess_id(), commandsList, commands.getProject());

        Gson gson = new Gson();
        String commandsJson = gson.toJson(commands1);

        commandsList.forEach(command -> {
            commandRepository.save(command);
            log.info("Command : " + command);
        });
        return commandsJson;
    }

    @Transactional
    @Override
    public List<Commands> getAllCommands() {
        List<Commands> commands = new ArrayList<>(commandsRepository.findAll());
        commands.forEach(commands1 -> getAllCommand());
        return commands;
    }

    @Transactional
    @Override
    public List<Command> getAllCommand() {
        return commandRepository.findAll();
    }

    @Override
    public List<Commands> getAllCommandsByProject_id(Long id) {
        return commandsRepository.listCommandsOfProject(id);
    }

    @Override
    public Commands getCommandsById(final Long id) {
        return commandsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public String DeleteCommands(final Long id) {
        return commandsRepository.findById(id)
                .map(commands -> {
                    commandsRepository.delete(commands);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("Your commands not found with id " + id));
    }
}
