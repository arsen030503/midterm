package com.example.springBoot.mappers;

import com.example.springBoot.dto.TaskDTO;
import com.example.springBoot.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TaskMapper {

    @Mapping(source = "userId", target = "user.id")
    Task taskDTOToTask(TaskDTO taskDTO);

    @Mapping(source = "user.id", target = "userId")
    TaskDTO taskToTaskDTO(Task task);
}
