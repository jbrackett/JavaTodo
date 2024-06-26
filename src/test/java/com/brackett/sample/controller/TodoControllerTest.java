package com.brackett.sample.controller;

import com.brackett.sample.domain.Todo;
import com.brackett.sample.exceptions.UnknownTodoException;
import com.brackett.sample.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPostTodo() throws Exception {
        var todo = new Todo(null, "test", true, false);
        var json = mapper.writeValueAsString(todo);
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        System.out.println("TODO: " + json);

        mockMvc.perform(post("/todos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.message").value("test"));
    }

    @Test
    public void testTodoNotFound() throws Exception {
        var uuid = Generators.timeBasedEpochGenerator().generate();
        when(todoRepository.findById(uuid)).thenThrow(new UnknownTodoException(""));
        mockMvc.perform(get("/todos/", uuid))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
