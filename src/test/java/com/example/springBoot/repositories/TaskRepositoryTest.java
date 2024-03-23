package com.example.springBoot.repositories;

import com.example.springBoot.entities.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testSaveTask() {
        Task task = new Task();
        task.setTitle("Complete Project");
        task.setDescription("Finish the backend development.");

        Task savedTask = taskRepository.save(task);
        assertNotNull(savedTask.getId());
        assertEquals("Complete Project", savedTask.getTitle());
        assertEquals("Finish the backend development.", savedTask.getDescription());
    }

    @Test
    public void testFindTaskById() {
        Task task = new Task();
        task.setTitle("Review Code");
        task.setDescription("Review pull requests and provide feedback.");

        Task savedTask = taskRepository.save(task);
        Optional<Task> optionalTask = taskRepository.findById(savedTask.getId());
        assertTrue(optionalTask.isPresent());
        assertEquals("Review Code", optionalTask.get().getTitle());
        assertEquals("Review pull requests and provide feedback.", optionalTask.get().getDescription());
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        task.setTitle("Update Documentation");
        task.setDescription("Update project documentation with latest changes.");

        Task savedTask = taskRepository.save(task);
        savedTask.setDescription("Update project documentation and add examples.");

        Task updatedTask = taskRepository.save(savedTask);
        assertEquals(savedTask.getId(), updatedTask.getId());
        assertEquals("Update Documentation", updatedTask.getTitle());
        assertEquals("Update project documentation and add examples.", updatedTask.getDescription());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task();
        task.setTitle("Delete Unused Files");
        task.setDescription("Remove unused files and clean up the project.");

        Task savedTask = taskRepository.save(task);
        taskRepository.delete(savedTask);

        Optional<Task> optionalTask = taskRepository.findById(savedTask.getId());
        assertFalse(optionalTask.isPresent());
    }
}
