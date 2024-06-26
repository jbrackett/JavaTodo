package com.brackett.sample.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.uuid.Generators;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @param id a v7 UUID that is generated when the record is created
 * @param message
 * @param completed
 * @param newOne a transient field that is used to determine if the record is new
 */
public record Todo(
        @Id UUID id,
        String message,
        Boolean completed,
        @Transient @JsonIgnore @Schema(hidden = true)
        Boolean newOne) implements Serializable, Comparable<Todo>, Persistable<UUID> {

    public Todo {
        if (id == null) {
            id = Generators.timeBasedEpochGenerator().generate();
            newOne = true;
        }
        else {
            newOne = false;
        }
        if (completed == null) {
            completed = false;
        }
    }

    /**
     * Constructor needed for Spring Data since `newOne` is not a property of the entity.
     */
    @PersistenceCreator
    public Todo(UUID id, String message, Boolean completed) {
        this(id, message, completed, false);
    }

    public Todo withMessage(String message) {
        return new Todo(id, message, completed);
    }

    public Todo withCompleted(Boolean completed) {
        return new Todo(id, message, completed);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return newOne;
    }

    @Override
    public int compareTo(Todo o) {
        return completed.compareTo(o.completed);
    }
}
