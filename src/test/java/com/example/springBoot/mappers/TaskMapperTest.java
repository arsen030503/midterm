package com.example.springBoot.mappers;

import com.example.springBoot.dto.TaskDTO;
import com.example.springBoot.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testTaskDtoToTask() {
        // Given
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Task Title");
        taskDTO.setDescription("Task Description");
        taskDTO.setUserId(1L);

        // When
        Task task = taskMapper.taskDTOToTask(taskDTO);

        // Then
        assertNotNull(task);
        assertEquals("Task Title", task.getTitle());
        assertEquals("Task Description", task.getDescription());
        assertEquals(1L, task.getUser().getId());
    }

    @Test
    public void testTaskToTaskDto() {
        // Given
        Task task = new Task();
        task.setTitle("Task Title");
        task.setDescription("Task Description");

        // When
        TaskDTO taskDTO = taskMapper.taskToTaskDTO(task);

        // Then
        assertNotNull(taskDTO);
        assertEquals("Task Title", taskDTO.getTitle());
        assertEquals("Task Description", taskDTO.getDescription());
    }
}
