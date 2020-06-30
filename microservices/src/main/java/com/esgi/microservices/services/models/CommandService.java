package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
<<<<<<< HEAD
import com.esgi.microservices.models.Commands;
=======
import com.esgi.microservices.models.Command;
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.ICommandService;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
=======
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService implements ICommandService {
<<<<<<< HEAD
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
=======
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
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
    }

    @Transactional
    @Override
<<<<<<< HEAD
    public List<Commands> getAllCommands() {
        List<Commands> commands = new ArrayList<>();
        commandRepository.findAll().forEach(commands::add);
        return commands;
    }

    @Override
    public Commands getById(final Long id) {
        return commandRepository.findById(id).orElse(null);
=======
    public List<Command> getAllCommands() {
         LOGGER.info("Find all commands");
        Iterable<Command> commands = commandRepository.findAll();
        return IteratorUtils.toList(commands.iterator());
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
    }

    @Transactional
    @Override
<<<<<<< HEAD
    public String DeleteCommands(final Long id) {
        return commandRepository.findById(id)
=======
    public List<Command> getCommands(Long projectid) {
        LOGGER.info("Find all commands");
        Project  project = projectRepository.findById(projectid)
                .orElseThrow(() -> new ResourceNotFoundException("commands not found with projectid " + projectid));
        List<Command> commands = project.getCommands();
                //(List<Command>) commandRepository.findByProjectId(projectid);
        return commands==null? Arrays.asList():commands;
    }

    @Transactional
    @Override
    public String DeleteCommands(Long Cmd_id) {
        return commandRepository.findById(Cmd_id)
>>>>>>> 924ccad81b3818f52558a0e31fe04d06cb802129
                .map(commands -> {
                    commandRepository.delete(commands);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("command not found with id " + id));
    }
}
