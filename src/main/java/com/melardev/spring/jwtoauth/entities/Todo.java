package com.melardev.spring.jwtoauth.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "todos")
public class Todo extends TimestampedEntity {


    private String title;

    // To avoid Caused by: org.h2.jdbc.JdbcSQLException: Value too long for column "DESCRIPTION VARCHAR(255)
    @Lob
    private String description;


    private boolean completed;

    public Long getId() {
        return id;
    }

    public Todo() {
    }

    public Todo(String title, String description) {
        this(title, description, false);
    }


    public Todo(Long id, String title, boolean completed, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Todo(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}