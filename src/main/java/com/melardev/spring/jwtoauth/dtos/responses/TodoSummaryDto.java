package com.melardev.spring.jwtoauth.dtos.responses;

import com.melardev.spring.jwtoauth.entities.Todo;

import java.time.ZonedDateTime;

public class TodoSummaryDto {
    private final String title;
    private final boolean completed;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime updatedAt;
    private final Long id;

    public TodoSummaryDto(Long id, String title, boolean completed, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
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

    public static TodoSummaryDto build(Todo todo) {
        return new TodoSummaryDto(todo.getId(), todo.getTitle(), todo.isCompleted(), todo.getCreatedAt(), todo.getUpdatedAt());
    }
}
