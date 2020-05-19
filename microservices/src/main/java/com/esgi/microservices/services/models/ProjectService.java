package com.esgi.microservices.services.models;

import com.esgi.microservices.kafka.KeySet;
import com.esgi.microservices.mapper.JsonMapperWrapper;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.repository.ProjectRepository;
import com.esgi.microservices.services.iservices.IProjectService;
import com.esgi.microservices.services.kafka.KafkaProducerService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final ProjectRepository projectRepository;
    private final KafkaProducerService kafkaProducerService;
    private final JsonMapperWrapper jsonMapper;
    private final String topic;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, KafkaProducerService kafkaProducerService, JsonMapperWrapper jsonMapper,@Value("${spring.kafka.consumer.topic.user}") String topic) {
        this.projectRepository = projectRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.jsonMapper = jsonMapper;
        this.topic = topic;
    }

    @Override
    public Project addProject(Project project) {
        LOGGER.info("Add Project: {}", project);
        Project addProject = projectRepository.save(project);
        kafkaProducerService.send(topic, KeySet.SAVE, jsonMapper.writeValue(addProject));
        return addProject;
    }

    @Override
    public List<Project> getProject() {
        LOGGER.info("Find all commands");
        Iterable<Project> projects = projectRepository.findAll();
        return IteratorUtils.toList(projects.iterator());
    }

    @Override
    public Optional<Project> getProjectByType(String type) {
        Optional<Project> project = projectRepository.findByType(type);
        if (project.isEmpty()) throw new RuntimeException("Type Project Not found!");
        return project;
    }
}
