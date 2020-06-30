package com.esgi.microservices.controllers;

import com.esgi.microservices.exception.ResourceNotFoundException;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.rabbitmq.Producer;
import com.esgi.microservices.services.models.CommandService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommadController {
    private static final Logger log = LogManager.getLogger(CommadController.class);

    private final RestTemplate restTemplate;

    private final CommandService commandService;

    private final Producer producer;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addCommmand(@RequestBody Commands commands) {
        log.info("< sendRequest bodyCommands:{}", commands.getCommand());
        // create a post object'[
        Commands savedCommands = commandService.addCommands(commands);
        String url = "http://127.0.0.1:8081/api/v1/commands/" + commands.getProcess_id();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        if (commands.getCommand().isEmpty() || commands.getCommand() == null) {
            throw new ResourceNotFoundException("Your commands is empty");
        } else {
            // build the request
            HttpEntity<Commands> entity = new HttpEntity<>(savedCommands, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            // check response status code
            if (response.getStatusCode() == HttpStatus.OK) {
                //send object to queue_commands
                producer.sendMessage(response.getBody());
                return response.getBody();
            } else {
                throw new ResourceNotFoundException("Request i'not send to client lourd!");
            }
        }
    }

    @GetMapping("/")
    public List<Commands> getCommands() {
        return commandService.getAllCommands();
    }

    @GetMapping("/{id}")
    public Commands getCommandById(@PathVariable Long id) {
        Commands getCommands = commandService.getById(id);
        return getCommands;
    }

    @DeleteMapping("/commands/{id}")
    public void getCommands(@PathVariable Long id) {
        commandService.DeleteCommands(id);
    }
}