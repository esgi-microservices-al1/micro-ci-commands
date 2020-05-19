package com.esgi.microservices.services.models;

import com.esgi.microservices.services.kafka.KafkaProducerService;
import org.apache.commons.collections4.IteratorUtils;
import com.esgi.microservices.kafka.KeySet;
import com.esgi.microservices.mapper.JsonMapperWrapper;
import com.esgi.microservices.models.User;
import com.esgi.microservices.repository.UserRepository;
import com.esgi.microservices.services.iservices.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final KafkaProducerService kafkaProducerService;
    private final JsonMapperWrapper jsonMapper;
    private final String topic;

    @Autowired
    public UserService(UserRepository userRepository, KafkaProducerService kafkaProducerService, JsonMapperWrapper jsonMapper,@Value("${spring.kafka.consumer.topic.user}") String topic) {
        this.userRepository = userRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.jsonMapper = jsonMapper;
        this.topic = topic;
    }

    @Transactional
    @Override
    public User addUsers(User user) {
        LOGGER.info("Add user: {}", user);
       User addUser = userRepository.save(user);
        kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(addUser));
        return addUser;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        LOGGER.info("Find all users");
        Iterable<User> users = userRepository.findAll();
        return IteratorUtils.toList(users.iterator());
    }

    @Transactional
    @Override
    public User getUsersById(Long id) {
        LOGGER.info("Find user, id: {}", id);
        return userRepository.findById(id).orElse(null);
    }
}
