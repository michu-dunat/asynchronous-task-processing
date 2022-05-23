package com.example.asynchronoustaskprocessing.services;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.RunningTask;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import com.example.asynchronoustaskprocessing.models.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {
    private ArrayList<Task> tasks = new ArrayList<>();

    public CreatedTask createTask(TaskArguments taskArguments) {
        int taskId;

        Double result = Math.pow(taskArguments.base, taskArguments.exponent);
        Task task = new Task(result);

        synchronized (tasks) {
            taskId = tasks.size() + 1;
            task.setId(taskId);
            tasks.add(task);
        }
        Thread thread = new Thread(task);
        thread.start();

        return new CreatedTask(taskId);
    }

    public Object getTask(Integer taskId) {
        synchronized (tasks) {
            Task task = tasks.get(taskId - 1);
            if(task.getStatus().equals(TaskStatus.RUNNING)) {
                return new RunningTask(task.getId(), task.getStatus(), task.getProgress());
            } else {
                return task;
            }
        }
    }

    public ArrayList<Object> getTasks() {
        ArrayList<Object> tasksToBeSent = new ArrayList<>();
        synchronized (tasks) {
            for (Task task : tasks) {
                if(task.getStatus().equals(TaskStatus.RUNNING)) {
                    tasksToBeSent.add(new RunningTask(task.getId(), task.getStatus(), task.getProgress()));
                } else {
                    tasksToBeSent.add(task);
                }
            }
        }
        return tasksToBeSent;
    }

}
