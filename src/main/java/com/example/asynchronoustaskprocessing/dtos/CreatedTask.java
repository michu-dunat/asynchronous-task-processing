package com.example.asynchronoustaskprocessing.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreatedTask {
    private Integer id;

    public CreatedTask(Integer id) {
        this.id = id;
    }

}
