package com.example.asynchronoustaskprocessing.services;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.models.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();

    public CreatedTask createTask(TaskArguments taskArguments) {
        Task task = new Task();
        Integer taskId = tasks.size() + 1;
        task.setId(taskId);
        tasks.add(task);
        return new CreatedTask(taskId);
    }

}
