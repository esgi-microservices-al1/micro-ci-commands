package com.esgi.microservices.rabbitmq;

import com.esgi.microservices.models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingKey}")
    private String routingkey;

    public void sendMessage(final String cmd) {
        try {
            String commandJson = objectMapper.writeValueAsString(cmd);
            Message message = MessageBuilder
                    .withBody(commandJson.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            //this.rabbitTemplate.convertAndSend(queueName, message);
            this.rabbitTemplate.convertAndSend(exchange, routingkey, message);
            log.info("Send Object = " + cmd.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
