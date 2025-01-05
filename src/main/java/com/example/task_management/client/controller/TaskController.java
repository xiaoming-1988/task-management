package com.example.task_management.client.controller;

import com.example.task_management.client.exception.NoSuchTaskException;
import com.example.task_management.dto.SimplePage;
import com.example.task_management.dto.TaskDto;
import com.example.task_management.entity.Task;
import com.example.task_management.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/task/")

@CrossOrigin //fixme: only for local testing
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("all")
    public SimplePage<TaskDto> getAllTask(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy,
                                          @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction) {

        Sort.Order order = new Sort.Order(direction, orderBy);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(order));

        Page<Task> taskPage =  taskService.retrieveTasks(pageRequest);

        return new SimplePage<>(
                taskPage.stream().map(TaskDto::new).toList()
                , taskPage.getTotalElements()
                , order.getDirection()
                , order.getProperty()
                , taskPage.getPageable().getPageNumber()
                , taskPage.getPageable().getPageSize()
        );
    }

    @GetMapping("{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        Task task =
                taskService.findTaskById(id)
                        .orElseThrow(NoSuchTaskException::new);

        return new TaskDto(task);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody TaskDto taskDto) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);

        return new TaskDto(
                taskService.createNewTask(task)
        );
    }

    @PutMapping("/{id}")
    public TaskDto createTask(@Valid @RequestBody TaskDto newTaskDto, @PathVariable Long id) {
        Task updatedTask =
                taskService
                        .findTaskById(id)
                        .map(
                                taskInDb -> {
                                    // only name, description and done can be changed for now
                                    taskInDb.setName(newTaskDto.getName());
                                    taskInDb.setDone(newTaskDto.isDone());
                                    taskInDb.setDescription(newTaskDto.getDescription());
                                    taskInDb.setPriority(newTaskDto.getPriority());

                                    return taskService.updateTask(taskInDb);
                                }
                        ).orElseThrow(NoSuchTaskException::new);

        return new TaskDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
