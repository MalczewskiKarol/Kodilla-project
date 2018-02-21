package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService service;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1, "test", "test"));

        when(repository.findAll()).thenReturn(taskList);
        //When
        List<Task> result = service.getAllTasks();
        //Then
        System.out.println(result);
        assertEquals(result.size(), 1);
        result.forEach(task -> {
            assertEquals(task.getId(), 1);
            assertEquals(task.getTitle(), "test");
            assertEquals(task.getContent(), "test");
        });
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1, "test", "test");
        when(repository.save(task)).thenReturn(task);
        //When
        service.saveTask(task);
        //Then
        assertNotNull(task);
    }

    @Test
    public void shouldGetTask() throws TaskNotFoundException {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1, "test", "test");
        taskList.add(task);
        when(repository.findById(1L)).thenReturn(java.util.Optional.ofNullable(task));
        //When
        service.getTask(1L).orElseThrow(TaskNotFoundException::new);
        //Then
        assertNotNull(task);
    }

    @Test
    public void shouldDeleteTask() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test", "test");
        taskList.add(task);
        doNothing().when(repository).deleteById(1L);
        //When
        service.deleteTask(1L);
        //Then
        verify(repository).deleteById(1L);
    }
}