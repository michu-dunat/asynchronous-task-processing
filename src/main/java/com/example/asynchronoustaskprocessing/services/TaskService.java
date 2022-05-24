package com.example.asynchronoustaskprocessing.services;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskWithResult;
import com.example.asynchronoustaskprocessing.dtos.TaskWithoutResult;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import com.example.asynchronoustaskprocessing.threads.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public CreatedTask createTask(TaskArguments taskArguments) {
        double result = Math.pow(taskArguments.base, taskArguments.exponent);
        Task task = createTaskAndAddItToTaskList(result);
        startTaskThread(task);
        return new CreatedTask(task.getId());
    }

    private Task createTaskAndAddItToTaskList(double powerResult) {
        Task task = new Task(powerResult);
        synchronized (tasks) {
            task.setId( tasks.size() + 1);
            tasks.add(task);
        }
        return task;
    }

    private void startTaskThread(Task task) {
        Thread thread = new Thread(task);
        thread.start();
    }

    public TaskWithoutResult getTask(Integer taskId) {
        synchronized (tasks) {
            if(tasks.isEmpty()) return null;
            Task task = tasks.get(taskId - 1);
            return (getTaskWithRemovedResultIfNeeded(task));
        }
    }

    private TaskWithoutResult getTaskWithRemovedResultIfNeeded(Task task) {
        if(task.getStatus().equals(TaskStatus.RUNNING)) {
            return new TaskWithoutResult(task.getId(), task.getStatus().getValue(), task.getProgress());
        } else {
            return new TaskWithResult(task.getId(), task.getStatus().getValue(), task.getProgress(), task.getResult());
        }
    }

    public ArrayList<TaskWithoutResult> getTasks() {
        ArrayList<TaskWithoutResult> tasksToBeSent;
        synchronized (tasks) {
            if(tasks.isEmpty()) return new ArrayList<>();

            tasksToBeSent = getAllTasksWithRemovedResultIfNeeded();
        }
        return tasksToBeSent;
    }

    private ArrayList<TaskWithoutResult> getAllTasksWithRemovedResultIfNeeded() {
        ArrayList<TaskWithoutResult> tasksToBeSent = new ArrayList<>();
        for (Task task : tasks) {
            fillTaskList(tasksToBeSent, task);
        }
        return tasksToBeSent;
    }

    private void fillTaskList(ArrayList<TaskWithoutResult> tasksToBeSent, Task task) {
        if(task.getStatus().equals(TaskStatus.RUNNING)) {
            tasksToBeSent.add(new TaskWithoutResult(task.getId(), task.getStatus().getValue(), task.getProgress()));
        } else {
            tasksToBeSent.add(new TaskWithResult(task.getId(), task.getStatus().getValue(), task.getProgress(),
                    task.getResult()));
        }
    }
}
