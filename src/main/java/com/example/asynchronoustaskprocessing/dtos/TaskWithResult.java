package com.example.asynchronoustaskprocessing.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskWithResult extends TaskWithoutResult{
    private Double result;

    public TaskWithResult(int id, String status, String progress, Double result) {
        super(id, status, progress);
        this.result = result;
    }
}
