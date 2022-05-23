package com.example.asynchronoustaskprocessing.controllers;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskWithoutResult;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping()
    public CreatedTask createTask(@RequestBody TaskArguments taskArguments) {
        return taskService.createTask(taskArguments);
    }

    @GetMapping("/{taskId}")
    public TaskWithoutResult checkStatusAndResultOfTask(@PathVariable Integer taskId) {
        return taskService.getTask(taskId);
    }

    @GetMapping()
    public List<TaskWithoutResult> checkStatusAndResultOfAllTasks() {
        return taskService.getTasks();
    }
}
