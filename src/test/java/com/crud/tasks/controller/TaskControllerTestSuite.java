package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTestSuite {

    @InjectMocks
    TaskController taskController;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private DbService service;

    @Test
    public void shouldGetTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "test", "test"));

        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1, "test", "test"));

        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);

        //When
        List<TaskDto> taskDtoList = taskController.getTasks();

        //Then
        assertNotNull(taskDtoList);
        taskDtoList.forEach(taskDto -> {
            assertEquals(taskDto.getId(), 1);
            assertEquals(taskDto.getTitle(), "test");
            assertEquals(taskDto.getContent(), "test");
        });
    }


    @Test
    public void shouldGetTask() throws TaskNotFoundException {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1, "test1", "test1");
        taskDtoList.add(taskDto);

        List<Task> tasks = new ArrayList<>();
        Task task = new Task(1L, "test", "test");
        tasks.add(task);

        when(service.getTask(1L)).thenReturn(ofNullable(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When
        TaskDto result = taskController.getTask(1L);
        //Then
        assertEquals(1, result.getId());
        assertEquals("test1", result.getTitle());
        assertEquals("test1", result.getContent());
    }

//    @Test
//    public void deleteTask() throws TaskNotFoundException {
//        //Given
//        Task task = new Task(1L, "test", "test");
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(task);
//
//        //When
//        taskController.deleteTask(1L);
//
//        //Then
//        assertTrue(taskList.isEmpty());
//    }

    @Test
    public void updateTask() {
        //Given
        TaskDto taskDto = new TaskDto(1, "test", "test");
        Task task = new Task(1L, "test", "test");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When
        TaskDto result = taskController.updateTask(taskDto);

        //Then
        assertEquals(result, taskDto);

    }

//    @Test
//    public void createTask() throws Exception {
//    }
}