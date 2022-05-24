package com.example.asynchronoustaskprocessing.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskWithoutResult {
    protected int id;
    protected String status;
    protected String progress;
}
