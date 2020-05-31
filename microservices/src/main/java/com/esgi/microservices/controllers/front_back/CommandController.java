package com.esgi.microservices.controllers;

import com.esgi.microservices.models.Commands;
import com.esgi.microservices.services.models.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/commands")
@RequiredArgsConstructor
public class CommadController {
    private final CommandService commandService;

    @PostMapping("/add")
    public void addCommmand(@Valid @RequestBody Commands commands){
        commandService.addCommands(commands);
    }

    @GetMapping("/commands")
    public List<Commands> getCommands(){
        return commandService.getAllCommands();
    }

    @DeleteMapping("/commands/{id}")
    public void getCommands(@PathVariable Long id){
         commandService.DeleteCommands(id);
    }
}
