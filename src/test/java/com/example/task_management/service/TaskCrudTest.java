package com.example.task_management.service;


import com.example.task_management.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskCrudTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void testCrud() {
        Task original = new Task();
        original.setName("test1");
        original.setType("A");
        original.setDone(false);
        original.setPriority(Task.PRIORITY.NORMAL);
        original.setDescription("just for test");


        // create
        Task createdTask = taskService.createNewTask(original);

        assertNotNull(createdTask);
        assertEquals(original.getName(), createdTask.getName());
        assertEquals(original.getType(), createdTask.getType());

        // retrieve
        Optional<Task> optionalTask = taskService.findTaskById(createdTask.getId());
        assertTrue(optionalTask.isPresent());
        createdTask = optionalTask.get();
        assertEquals(original.getName(), createdTask.getName());
        assertEquals(original.getType(), createdTask.getType());


        // update
        var newName = "new name now";
        createdTask.setName(newName);
        Task updatedTask = taskService.updateTask(createdTask);
        assertNotNull(updatedTask);
        assertEquals(updatedTask.getId(), createdTask.getId());
        assertEquals(updatedTask.getName(), newName);

        // page query
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(order));

        Page<Task> taskPage = taskService.retrieveTasks(pageRequest);
        assertTrue(taskPage.get().findAny().isPresent());

        // delete
        taskService.deleteTask(createdTask.getId());
        Optional<Task> shouldNotExistedTask = taskService.findTaskById(createdTask.getId());
        assertFalse(shouldNotExistedTask.isPresent());

        taskPage = taskService.retrieveTasks(pageRequest);
        assertFalse(taskPage.get().findAny().isPresent());
    }
}
