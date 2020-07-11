package com.esgi.microservices.servicestest;

import com.esgi.microservices.models.Command;
import com.esgi.microservices.services.models.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandServiceTest {
    protected CommandService commandService;

    @BeforeEach
    protected void setup() {
        commandService = mock(CommandService.class);
    }

    @Test
    void should_command_empty_after_init() {
        given(commandService.getAllCommand()).willReturn(new ArrayList<>());
        assertTrue(commandService.getAllCommand().isEmpty());
    }

    @Test
    void should_return_all_command() {
        ArrayList<Command> commandList = new ArrayList<>();
        commandList.add(new Command());
        commandList.add(new Command());
        commandList.add(new Command());

        given(commandService.getAllCommand()).willReturn(commandList);
        assertFalse(commandList.isEmpty());
        assertEquals(3, commandService.getAllCommand().size());
    }

    @Test
    void should_return_command_with_id() {
        Command command = new Command();
        given(commandService.getCommandById(1L)).willReturn(command);
        assertNotNull(commandService.getCommandById(1L));
    }


    @Test
    void should_delete_throw_exception_command() {
        Command commandTest = new Command();
        commandTest.setId(1L);
        when(commandService.deleteCommand(eq(-1L))).thenThrow(new RuntimeException("error id"));
    }

}
