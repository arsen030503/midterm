package com.example.springBoot.services;

import com.example.springBoot.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO getTaskById(Long taskId);
    List<TaskDTO> getAllTasks();
    TaskDTO updateTask(Long taskId, TaskDTO taskDTO);
    void deleteTask(Long taskId);
    TaskDTO partiallyUpdateTask(Long taskId, TaskDTO taskDTO);
}
