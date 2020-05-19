package com.esgi.microservices.services.kafka;

import com.esgi.microservices.kafka.KeySet;
import com.esgi.microservices.services.iservices.IKafkaProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService implements IKafkaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, KeySet key, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}', key='{}'", payload, topic, key);
        kafkaTemplate.send(topic, key.toString(), payload);
    }

}
