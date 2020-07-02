package com.esgi.microservices.rabbitmq;

import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    private final ProjectService projectService;


    @RabbitListener(queues = {"al1-project-queue"})
    @RabbitHandler
    public void recievedMessage(Project project) {
        //project.setProject_id(project.getProject_id()+1);
        //projectService.addProject(project);
        log.info("Recieved Message body commands: [" + project.toString() + "] ");
    }
}
