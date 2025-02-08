package com.example.task_management.service;

import com.example.task_management.entity.Task;
import com.example.task_management.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskService {

    private static Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> retrieveTasks(PageRequest pageRequest) {

        return taskRepository.findAll(pageRequest);
    }

    public Task createNewTask(Task task) {
        task.setCreated(LocalDateTime.now());
        task.setUpdated(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> findTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteTask(Long id) {
        taskRepository.findById(id).ifPresent(
                task -> {
                    logger.info("deleting the taskId: {}, taskName:{}", task.getId(), task.getName());
                }
        );

        taskRepository.deleteById(id);
    }

}
