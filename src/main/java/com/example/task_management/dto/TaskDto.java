package com.example.task_management.dto;

import com.example.task_management.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;

    @NotEmpty(message = "name is required")
    @Size(min = 1, max = 64, message = "task name should be between 1 and 64 characters")
    private String name;

    @NotEmpty(message = "type is required")
    private String type;

    private boolean done;

    @NotNull(message = "priority is required")
    private Long priority;

    private LocalDateTime created;

    private LocalDateTime updated;

    @Size(max = 256, message = "task description should less than 256 characters")
    private String description;

    public TaskDto (Task task) {
        BeanUtils.copyProperties(task, this);
    }

}
