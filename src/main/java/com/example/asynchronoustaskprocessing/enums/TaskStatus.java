package com.example.asynchronoustaskprocessing.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaskStatus {
    FINISHED("finished"),
    RUNNING("running"),
    ERROR("error");

    private final String value;
}
