package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TaskMapperTestSuite {

    @Test
    public void mapToTask() {
        //Given
        TaskDto dto = new TaskDto(1, "test", "test");
        TaskMapper mapper = new TaskMapper();
        //When
        mapper.mapToTask(dto);
        //Then
        assertNotNull(dto);
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task();
        TaskMapper mapper = new TaskMapper();
        //When
        mapper.mapToTaskDto(task);
        //Then
        assertNotNull(task);
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        TaskMapper mapper = new TaskMapper();
        //When
        mapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskList);
    }

}