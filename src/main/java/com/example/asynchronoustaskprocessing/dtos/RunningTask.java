package com.example.asynchronoustaskprocessing.dtos;

import com.example.asynchronoustaskprocessing.enums.TaskStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RunningTask {
    private Integer id;
    private TaskStatus status;
    private String progress;

}
