package com.example.springBoot.services;

import com.example.springBoot.dto.TaskDTO;
import com.example.springBoot.entities.Task;
import com.example.springBoot.mappers.TaskMapper;
import com.example.springBoot.repositories.TaskRepository;
import com.example.springBoot.services.TaskServiceJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceJPAUnitTest {

    @Autowired
    private TaskServiceJPA taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void testGetTaskById() {
        // Mocking repository behavior
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Sample Task");
        task.setDescription("This is a sample task");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Mocking mapper behavior
        TaskDTO expectedTaskDTO = new TaskDTO(task);
        expectedTaskDTO.setId(1L);
        expectedTaskDTO.setTitle("Sample Task");
        expectedTaskDTO.setDescription("This is a sample task");
        when(taskMapper.taskToTaskDTO(task)).thenReturn(expectedTaskDTO);

        // Calling service method
        TaskDTO actualTaskDTO = taskService.getTaskById(1L);

        // Verifying result
        assertEquals(expectedTaskDTO.getId(), actualTaskDTO.getId());
        assertEquals(expectedTaskDTO.getTitle(), actualTaskDTO.getTitle());
        assertEquals(expectedTaskDTO.getDescription(), actualTaskDTO.getDescription());
    }

    @Test
    void testGetAllTasks() {
        // Mocking repository behavior
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");

        taskList.add(task1);
        taskList.add(task2);

        when(taskRepository.findAll()).thenReturn(taskList);

        // Mocking mapper behavior
        when(taskMapper.taskToTaskDTO(task1)).thenReturn(new TaskDTO(task1));
        when(taskMapper.taskToTaskDTO(task2)).thenReturn(new TaskDTO(task2));

        // Calling service method
        List<TaskDTO> actualTaskDTOList = taskService.getAllTasks();

        // Verifying result
        assertEquals(taskList.size(), actualTaskDTOList.size());
        for (int i = 0; i < taskList.size(); i++) {
            Task expectedTask = taskList.get(i);
            TaskDTO actualTaskDTO = actualTaskDTOList.get(i);
            assertEquals(expectedTask.getId(), actualTaskDTO.getId());
            assertEquals(expectedTask.getTitle(), actualTaskDTO.getTitle());
            assertEquals(expectedTask.getDescription(), actualTaskDTO.getDescription());
        }
    }

    @Test
    void testDeleteTask() {
        // Calling service method
        taskService.deleteTask(1L);

        // Verifying repository method invocation
        verify(taskRepository, times(1)).deleteById(1L);
    }

    // Add more test methods for other functionalities of TaskServiceJPA as needed
}
