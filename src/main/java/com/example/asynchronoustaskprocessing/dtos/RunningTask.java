package com.example.asynchronoustaskprocessing.dtos;

import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RunningTask {
    private Integer id;
    private TaskStatus status;
    private String progress;

}
