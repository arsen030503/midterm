package com.example.springBoot.bootstrap;

import com.example.springBoot.entities.User;
import com.example.springBoot.entities.Task;
import com.example.springBoot.repositories.UserRepository;
import com.example.springBoot.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public BootstrapDataLoader(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create 15 users
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setEmail("user" + i + "@example.com");
            users.add(user);
        }
        userRepository.saveAll(users);

        // Create 5 tasks for each user
        for (User user : users) {
            for (int i = 1; i <= 5; i++) {
                Task task = new Task();
                task.setTitle("Task " + i + " for " + user.getUsername());
                task.setDescription("Description of Task " + i + " for " + user.getUsername());
                task.setUser(user);
                taskRepository.save(task);
            }
        }
    }
}
