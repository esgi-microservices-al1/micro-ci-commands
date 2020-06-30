package com.esgi.microservices.dto.factory;

import com.esgi.microservices.dto.CommandDto;
import com.esgi.microservices.dto.ProjectDto;
import com.esgi.microservices.models.Command;
import com.esgi.microservices.models.Project;
import com.esgi.microservices.services.DateUtils;

import java.text.ParseException;

public class CommandMaker implements  Converter<Command, CommandDto> {
    private Project project;
    private ProjectDto projectDto;

    public CommandMaker(Project project) {
        this.project = project;
        this.projectDto = null;
    }

    public CommandMaker(ProjectDto dto) {
        this.projectDto = dto;
        this.project = null;
    }

    @Override
    public Command convertToEntity(CommandDto commandDto) throws ParseException {
        if(project!=null){
            Command command = new Command();
            command.setCommand(commandDto.getCommand() != null ? commandDto.getCommand() : null);
            command.setAffichable(commandDto.isAffichable() ? true : false);
            command.setDatecreated(commandDto.getDatecreated()!=null ? commandDto.getDatecreated()
                    : DateUtils.todayWithHour());
            command.setProject(project);
            return command;
        }
        return null;
    }

    @Override
    public CommandDto convertToDto(Command command) {
        if(project!=null){
            CommandDto dto = new CommandDto();
            dto.setCmdid(command.getCmdid() != null ? command.getCmdid() : null);
            dto.setCommand(command.getCommand() != null ? command.getCommand() : null);
            dto.setAffichable(command.isAffichable() ? true : false);
            dto.setDatecreated(command.getDatecreated() != null ? command.getCommand() : null);
            dto.setProjectid(command.getProject().getId());
            return dto;
        }
        return null;
    }
}
