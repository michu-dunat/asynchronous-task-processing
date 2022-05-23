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
        double result = Math.pow(taskArguments.base, taskArguments.exponent);
        TaskWithResult task = createTaskAndAddItToTaskList(result);
        startTaskThread(task);
        return new CreatedTask(task.getId());
    }

    private TaskWithResult createTaskAndAddItToTaskList(double powerResult) {
        TaskWithResult task = new TaskWithResult(powerResult);
        synchronized (tasks) {
            task.setId( tasks.size() + 1);
            tasks.add(task);
        }
        return task;
    }

    private void startTaskThread(TaskWithResult task) {
        Thread thread = new Thread(task);
        thread.start();
    }

    public TaskWithoutResult getTask(Integer taskId) {
        synchronized (tasks) {
            TaskWithResult task = tasks.get(taskId - 1);
            return (getTaskWithRemovedResultIfNeeded(task));
        }
    }

    private TaskWithoutResult getTaskWithRemovedResultIfNeeded(TaskWithResult task) {
        if(task.getStatus().equals(TaskStatus.RUNNING)) {
            return new TaskWithoutResult(task.getId(), task.getStatus(), task.getProgress());
        } else {
            return task;
        }
    }

    public ArrayList<TaskWithoutResult> getTasks() {
        ArrayList<TaskWithoutResult> tasksToBeSent;
        synchronized (tasks) {
            tasksToBeSent = getAllTasksWithRemovedResultIfNeeded();
        }
        return tasksToBeSent;
    }

    private ArrayList<TaskWithoutResult> getAllTasksWithRemovedResultIfNeeded() {
        ArrayList<TaskWithoutResult> tasksToBeSent = new ArrayList<>();
        for (TaskWithResult task : tasks) {
            fillTaskList(tasksToBeSent, task);
        }
        return tasksToBeSent;
    }

    private void fillTaskList(ArrayList<TaskWithoutResult> tasksToBeSent, TaskWithResult task) {
        if(task.getStatus().equals(TaskStatus.RUNNING)) {
            tasksToBeSent.add(new TaskWithoutResult(task.getId(), task.getStatus(), task.getProgress()));
        } else {
            tasksToBeSent.add(task);
        }
    }
}
