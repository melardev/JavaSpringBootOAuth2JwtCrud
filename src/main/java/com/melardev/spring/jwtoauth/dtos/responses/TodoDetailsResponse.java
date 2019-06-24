package com.melardev.spring.jwtoauth.dtos.responses;


import com.melardev.spring.jwtoauth.entities.Todo;

import java.time.ZonedDateTime;

public class TodoDetailsResponse extends SuccessResponse {
    private final Long id;
    private String title, description;
    private boolean completed;

    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public TodoDetailsResponse(Long id, String title, String description, boolean completed, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TodoDetailsResponse(Todo todo, String message) {
        this(todo.getId(), todo.getTitle(), todo.getDescription(), todo.isCompleted(), todo.getCreatedAt(), todo.getUpdatedAt());
        addFullMessage(message);
    }

    public TodoDetailsResponse(Todo todo) {
        this(todo, null);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getId() {
        return id;
    }
}
