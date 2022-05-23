package com.example.asynchronoustaskprocessing.dtos;

import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithoutResult {
    protected int id;
    protected TaskStatus status;
    protected String progress;
}
