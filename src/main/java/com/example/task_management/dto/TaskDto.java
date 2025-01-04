package com.example.task_management.dto;

import com.example.task_management.entity.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TaskDto {
    private Long id;

    private String name;

    private String type;

    private boolean done;

    private Long priority;

    private LocalDateTime created;

    private LocalDateTime updated;

    private String description;

    public TaskDto (Task task) {
        BeanUtils.copyProperties(task, this);
    }

}
