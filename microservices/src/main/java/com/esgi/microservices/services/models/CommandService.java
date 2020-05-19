package com.esgi.microservices.services.models;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.kafka.KeySet;
import com.esgi.microservices.mapper.JsonMapperWrapper;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.models.User;
import com.esgi.microservices.repository.CommandRepository;
import com.esgi.microservices.services.iservices.ICommandService;
import com.esgi.microservices.services.kafka.KafkaProducerService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandService implements ICommandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final CommandRepository commandRepository;
    private final KafkaProducerService kafkaProducerService;
    private final JsonMapperWrapper jsonMapper;
    private final String topic;

    @Autowired
    public CommandService(CommandRepository commandRepository, KafkaProducerService kafkaProducerService, JsonMapperWrapper jsonMapper,@Value("${spring.kafka.consumer.topic.user}") String topic) {
        this.commandRepository = commandRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.jsonMapper = jsonMapper;
        this.topic = topic;
    }

    @Transactional
    @Override
    public Commands addCommands(Commands commands) {
        LOGGER.info("Add commands: {}", commands);
        Commands addCmd = commandRepository.save(commands);
        kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(addCmd));
        return addCmd;
    }

    @Transactional
    @Override
    public List<Commands> getAllCommands() {
         LOGGER.info("Find all commands");
        Iterable<Commands> commands = commandRepository.findAll();
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
