package com.brackett.sample.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    @Test
    public void testComparison() {
        var l = new ArrayList<Todo>();

        var todo1 = new Todo(null, "message1", false);
        var todo2 = new Todo(null, "message2", true);
        var todo3 = new Todo(null, "message3", false);

        l.add(todo1);
        l.add(todo2);
        l.add(todo3);

        assertFalse(l.get(0).completed());
        assertTrue(l.get(1).completed());
        assertFalse(l.get(2).completed());

        l.sort(null);

        assertFalse(l.get(0).completed());
        assertFalse(l.get(1).completed());
        assertTrue(l.get(2).completed());
    }
}
