package com.example.asynchronoustaskprocessing.models;

import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Task implements Runnable {
    private Integer id;
    private TaskStatus status;
    private String progress;
    private Double result;

    public Task(Double result) {
        this.result = result;
        this.status = TaskStatus.RUNNING;
        this.progress = "0%";
    }

    @Override
    public void run() {
        Random random = new Random();
        int taskMaxTime = random.nextInt(35000 - 25000) + 25000;
        int taskTimeLeft = taskMaxTime;
        int interval = taskMaxTime / 100;

        while(taskTimeLeft > 0) {
            try {
                Thread.sleep(interval);
                taskTimeLeft -= interval;
                float percentage =  ((float) taskTimeLeft / (float) taskMaxTime) * 100;
                if(percentage > 0) {
                    progress = (100 - Math.floor(percentage)) + "%";
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        status = TaskStatus.FINISHED;
        progress = "100%";
    }
}
