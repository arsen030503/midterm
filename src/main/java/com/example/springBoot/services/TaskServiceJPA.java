package com.example.springBoot.services;

import com.example.springBoot.dto.TaskDTO;
import com.example.springBoot.entities.Task;
import com.example.springBoot.mappers.TaskMapper;
import com.example.springBoot.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceJPA implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    public TaskServiceJPA(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task = taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::taskToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("User not found"));
        return taskMapper.taskToTaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if(existingTask == null) {
            return null;
        }
        existingTask = taskRepository.save(existingTask);
        return taskMapper.taskToTaskDTO(existingTask);

    }

    @Override
    public TaskDTO partiallyUpdateTask(Long taskId, TaskDTO taskDTO) {
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (taskDTO.getTitle() != null) {
            existingTask.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            existingTask.setDescription(taskDTO.getDescription());
        }
        // Apply additional partial updates as needed
        existingTask = taskRepository.save(existingTask);
        existingTask = taskRepository.save(existingTask);
        return taskMapper.taskToTaskDTO(existingTask);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
