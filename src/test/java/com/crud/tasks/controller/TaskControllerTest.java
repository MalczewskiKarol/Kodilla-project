package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper mapper;

    @Test
    public void shouldGetTasks() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1, "test taskDto", "test content");
        taskDtoList.add(taskDto);

        when(mapper.mapToTaskDtoList(ArgumentMatchers.anyList())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test taskDto")))
                .andExpect(jsonPath("$[0].content", is("test content")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1, "test", "test");
        taskDtoList.add(taskDto);
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1, "test1", "test");
        taskList.add(task);

        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(task));
        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test")))
                .andExpect(jsonPath("$.content", is("test")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "test", "test");
        taskList.add(task);

//        when(dbService.getTask(1L)).thenReturn(Optional.ofNullable(task));
        doNothing().when(dbService).deleteTask(1L);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(dbService, times(1)).deleteTask(1L);
        verifyNoMoreInteractions(dbService);
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test", "test content");
        TaskDto taskDto = new TaskDto(1L, "test", "test content");

        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1, "test", "test");
        Task task = new Task(1, "test", "test");

        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
                .param("title", "test")
                .param("content", "test"))
                .andExpect(status().isOk());
    }
}