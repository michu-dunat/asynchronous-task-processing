package com.example.asynchronoustaskprocessing;

import com.example.asynchronoustaskprocessing.dtos.CreatedTask;
import com.example.asynchronoustaskprocessing.dtos.TaskArguments;
import com.example.asynchronoustaskprocessing.dtos.TaskWithoutResult;
import com.example.asynchronoustaskprocessing.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskServiceTests {
    @InjectMocks
    private TaskService taskService;

    TaskArguments taskArguments;

    @BeforeEach
    void setUp() {
        taskArguments = new TaskArguments();
        taskArguments.setBase(2);
        taskArguments.setExponent(4);
    }

    @Test
    void shouldReturnIdOf1IfOneTaskIsCreated() {
        CreatedTask createdTask = taskService.createTask(taskArguments);
        assertThat(createdTask.getId()).isEqualTo(1);
    }

    @Test
    void shouldReturnIdOf2IfOneTaskIsCreated() {
        taskService.createTask(taskArguments);
        CreatedTask createdTask = taskService.createTask(taskArguments);
        assertThat(createdTask.getId()).isEqualTo(2);
    }

    @Test
    void shouldReturnNullIfAskedForTaskThatWasNotCreated() {
        TaskWithoutResult taskWithoutResult = taskService.getTask(1);
        assertThat(taskWithoutResult).isNull();
    }

    @Test
    void shouldReturnTaskWithIdOf1IfItWasCreated() {
        shouldReturnIdOf1IfOneTaskIsCreated();

        TaskWithoutResult taskWithoutResult = taskService.getTask(1);
        assertThat(taskWithoutResult).isNotNull();
        assertThat(taskWithoutResult.getId()).isEqualTo(1);
    }

    @Test
    void shouldReturnEmptyListIfAskedForTasksWhenNoneWasCreated() {
        ArrayList<TaskWithoutResult> tasksWithoutResult = taskService.getTasks();
        assertThat(tasksWithoutResult).isEmpty();
    }

    @Test
    void shouldReturnListWithOneTaskIfOnlyOneWasCreated() {
        taskService.createTask(taskArguments);
        ArrayList<TaskWithoutResult> tasksWithoutResult = taskService.getTasks();
        assertThat(tasksWithoutResult).isNotEmpty();
        assertThat(tasksWithoutResult.get(0)).isNotNull();
        assertThat(tasksWithoutResult.get(0).getId()).isEqualTo(1);
    }

    @Test
    void shouldReturnListWithTasksIfTheyWereCreated() {
        taskService.createTask(taskArguments);
        taskService.createTask(taskArguments);
        ArrayList<TaskWithoutResult> tasksWithoutResult = taskService.getTasks();
        assertThat(tasksWithoutResult.size()).isEqualTo(2);
        assertThat(tasksWithoutResult.get(0)).isNotNull();
        assertThat(tasksWithoutResult.get(0).getId()).isEqualTo(1);
        assertThat(tasksWithoutResult.get(1)).isNotNull();
        assertThat(tasksWithoutResult.get(1).getId()).isEqualTo(2);
    }

}
