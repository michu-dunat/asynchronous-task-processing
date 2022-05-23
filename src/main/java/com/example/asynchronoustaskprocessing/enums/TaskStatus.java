package com.example.asynchronoustaskprocessing.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TaskStatus {
    FINISHED("finished"),
    RUNNING("running");

    private String value;

}
