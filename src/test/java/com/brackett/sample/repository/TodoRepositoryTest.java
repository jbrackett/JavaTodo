package com.brackett.sample.repository;

import com.brackett.sample.domain.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testSaveTodo() {
        var todo = new Todo(null, "test", false, false);
        var result = todoRepository.save(todo);
        assertNotNull(result);
        assertNotNull(result.id());
        assertFalse(result.completed());
        assertTrue(result.isNew());
    }
}
