package com.example.springBoot.dto;

import com.example.springBoot.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
    }
    private Long id;
    private String title;
    private String description;
    private Long userId; // Changed to userId
}
