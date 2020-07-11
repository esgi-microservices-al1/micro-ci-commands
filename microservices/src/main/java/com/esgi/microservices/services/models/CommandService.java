package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.services.iservices.ICommandService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService implements ICommandService {
    private static final Logger log = LoggerFactory.getLogger(CommandsService.class);

    private final CommandRepository commandRepository;

    @Transactional
    @Override
    public List<Command> getAllCommand() {
        return commandRepository.findAll();
    }

    @Override
    public Command getCommandById(final Long id) {
        Optional<Command> search = commandRepository.findById(id);
        if (search.isEmpty() && !search.isPresent()) {
            throw new ResourceNotFoundException("Your command_id for command not found");
        } else {
            return commandRepository.findById(id).orElse(null);
        }
    }

    @Transactional
    @Override
    public String deleteCommand(final Long id) {
        return commandRepository.findById(id)
                .map(commands -> {
                    commandRepository.delete(commands);
                    return "Delete Successfully!";
                }).orElseThrow(() -> new ResourceNotFoundException("command not found with id " + id));
    }
}
