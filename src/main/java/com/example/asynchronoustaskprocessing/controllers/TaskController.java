package com.example.asynchronoustaskprocessing.controllers;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    public CreatedTask createTask(@RequestBody TaskArguments taskArguments) {
        return taskService.createTask(taskArguments);
    }

}
