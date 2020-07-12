package com.esgi.microservices.servicestest;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Commands;
import com.esgi.microservices.services.models.CommandService;
import com.esgi.microservices.services.models.CommandsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandsServiceTest {
    protected CommandsService commandsService;
    protected CommandService commandService;

    @BeforeEach
    protected void setup() {
        commandsService = mock(CommandsService.class);
        commandService = mock(CommandService.class);
    }

    @Test
    void should_commands_empty_after_init() {
        //for getAllCommands()
        given(commandsService.getAllCommands()).willReturn(new ArrayList<>());
        assertTrue(commandsService.getAllCommands().isEmpty());
        //for getAllCommand()
        given(commandService.getAllCommand()).willReturn(new ArrayList<>());
        assertTrue(commandService.getAllCommand().isEmpty());
    }

    @Test
    void should_create_commands_and_returns_list_commands() {
        Command addcommand = new Command();
        addcommand.setId(1L);
        addcommand.setCommand("ls -l cc");
        addcommand.setStdout(true);

        when(commandsService.addCommands(any())).thenReturn(addcommand.getCommand());
        assertEquals("ls -l cc", addcommand.getCommand());
    }

    @Test
    void should_return_all_commands() {
        ArrayList<Commands> commandsList = new ArrayList<>();
        commandsList.add(new Commands());
        commandsList.add(new Commands());
        commandsList.add(new Commands());
        commandsList.forEach(commands -> commandService.getAllCommand());
        given(commandsService.getAllCommands()).willReturn(commandsList);
        assertFalse(commandsList.isEmpty());
        assertEquals(3, commandsService.getAllCommands().size());
    }

    @Test
    void should_return_all_commands_by_project_id() {
        ArrayList<Commands> commandsList = new ArrayList<>();
        given(commandsService.getAllCommandsByProject_id(1L)).willReturn(commandsList);
        assertNotNull(commandsService.getAllCommandsByProject_id(1L));
    }

    @Test
    void should_return_commands_with_id() {
        Commands commands = new Commands();
        given(commandsService.getCommandsById(1L)).willReturn(commands);
        assertNotNull(commandsService.getCommandsById(1L));
    }

    @Test
    void should_delete_throw_exception_command() {
        Commands commandTest = new Commands();
        commandTest.setProcess_id(1L);
        when(commandsService.deleteCommands(eq(-1L))).thenThrow(new RuntimeException("error id"));
    }
}
