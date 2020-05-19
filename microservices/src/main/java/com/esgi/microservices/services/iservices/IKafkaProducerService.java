package com.esgi.microservices.services.iservices;

import com.esgi.microservices.kafka.KeySet;

public interface IKafkaProducerService {
    void send(String topic, KeySet key, String payload);
}
