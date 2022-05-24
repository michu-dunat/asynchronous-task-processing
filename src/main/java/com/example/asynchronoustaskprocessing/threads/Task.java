package com.example.asynchronoustaskprocessing.threads;

import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Task implements Runnable {
    private int id;
    private Double result;
    private TaskStatus status;
    private String progress;

    public Task(double result) {
        this.result = result;
        this.status = TaskStatus.RUNNING;
        this.progress = "0%";
    }

    @Override
    public void run() {
        int taskMaxTime = getRandomTaskDuration(35000, 25000);
        int taskTimeLeft = taskMaxTime;
        int interval = taskMaxTime / 100;

        while(taskTimeLeft > 0) {
            try {
                Thread.sleep(interval);
                taskTimeLeft -= interval;
                calculateAndUpdateProgress((float) taskTimeLeft, (float) taskMaxTime);
            } catch (InterruptedException e) {
                setTaskFieldsToErrorValues();
            }
        }

        setStatusAndProgressToCompletionState();
    }

    private void setTaskFieldsToErrorValues() {
        progress = "-1";
        status = TaskStatus.ERROR;
        result = null;
    }

    private void calculateAndUpdateProgress(float taskTimeLeft, float taskMaxTime) {
        float percentage =  (taskTimeLeft / taskMaxTime) * 100;
        if(percentage > 0) {
            progress = (100 - Math.floor(percentage)) + "%";
        }
    }

    private int getRandomTaskDuration(int upperBoundInMilliSeconds, int lowerBoundInMilliSeconds) {
        Random random = new Random();
        return random.nextInt(upperBoundInMilliSeconds - lowerBoundInMilliSeconds) + lowerBoundInMilliSeconds;
    }

    private void setStatusAndProgressToCompletionState() {
        status = TaskStatus.FINISHED;
        progress = "100%";
    }
}
