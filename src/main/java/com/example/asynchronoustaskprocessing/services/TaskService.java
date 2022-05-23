package com.example.asynchronoustaskprocessing.services;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskWithoutResult;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import com.example.asynchronoustaskprocessing.threads.TaskWithResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final ArrayList<TaskWithResult> tasks = new ArrayList<>();

    public CreatedTask createTask(TaskArguments taskArguments) {
        int taskId;

        double result = Math.pow(taskArguments.base, taskArguments.exponent);
        TaskWithResult task = new TaskWithResult(result);

        synchronized (tasks) {
            taskId = tasks.size() + 1;
            task.setId(taskId);
            tasks.add(task);
        }
        Thread thread = new Thread(task);
        thread.start();

        return new CreatedTask(taskId);
    }

    public TaskWithoutResult getTask(Integer taskId) {
        synchronized (tasks) {
            TaskWithResult task = tasks.get(taskId - 1);
            if(task.getStatus().equals(TaskStatus.RUNNING)) {
                return new TaskWithoutResult(task.getId(), task.getStatus(), task.getProgress());
            } else {
                return task;
            }
        }
    }

    public ArrayList<TaskWithoutResult> getTasks() {
        ArrayList<TaskWithoutResult> tasksToBeSent = new ArrayList<>();
        synchronized (tasks) {
            for (TaskWithResult task : tasks) {
                if(task.getStatus().equals(TaskStatus.RUNNING)) {
                    tasksToBeSent.add(new TaskWithoutResult(task.getId(), task.getStatus(), task.getProgress()));
                } else {
                    tasksToBeSent.add(task);
                }
            }
        }
        return tasksToBeSent;
    }

}
