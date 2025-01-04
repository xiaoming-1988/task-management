package com.example.task_management.dto;

import com.example.task_management.entity.Task;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

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

    public TaskDto() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
