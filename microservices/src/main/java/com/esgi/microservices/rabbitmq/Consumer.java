package com.esgi.microservices.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

//    @RabbitListener(queues = "${rabbitmq.queue}")
//    public void recievedMessage(Commands cmd) {
//       // Commands commands =  new ObjectMapper().readValue(cmd, Commands.class);
//        log.info("Recieved Message body commands: " + cmd.toString());
//    }
}
