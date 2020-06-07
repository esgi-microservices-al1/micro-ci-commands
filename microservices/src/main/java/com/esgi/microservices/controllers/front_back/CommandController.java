package com.esgi.microservices.controllers.front_back;

import com.esgi.microservices.dto.CommandDto;
import com.esgi.microservices.dto.factory.CommandMaker;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.models.CommandService;
import com.esgi.microservices.services.models.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/commands")
public class CommandController {
    @Autowired
    private CommandService commandService;
    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Boolean> addCommmand(@Valid @RequestBody CommandDto commandDto) throws ParseException {
        Project project = projectService.getProjectId(commandDto.getProjectid());
        CommandMaker maker = new CommandMaker(project);
        if(commandService.addCommands(maker.convertToEntity(commandDto),project ) == null){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public List<CommandDto> getCommands(){
        List<Command> commmands = commandService.getAllCommands();
        List<CommandDto> dtos = new ArrayList<>();
        for(Command c : commmands){
            CommandMaker maker = new CommandMaker(c.getProject());
            dtos.add(maker.convertToDto(c));
        }
        return dtos;
    }

    @GetMapping("/command/{id}")
    public ResponseEntity<List<CommandDto>> getCommandsWithId(@PathVariable(value = "id") Long id){
        List<Command> commmands = commandService.getCommands(id);
        List<CommandDto> dtos = new ArrayList<>();
        for(Command c : commmands){
            CommandMaker maker = new CommandMaker(c.getProject());
            dtos.add(maker.convertToDto(c));
        }
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/command/{id}")
    public void getCommands(@PathVariable Long id){
         commandService.DeleteCommands(id);
    }
}
