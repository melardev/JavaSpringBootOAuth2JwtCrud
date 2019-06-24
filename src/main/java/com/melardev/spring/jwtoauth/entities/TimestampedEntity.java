package com.melardev.spring.jwtoauth.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;


@MappedSuperclass
public abstract class TimestampedEntity implements Comparable<TimestampedEntity>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;

    @Column(nullable = false)
    protected ZonedDateTime createdAt;

    @Column(nullable = false)
    protected ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = ZonedDateTime.now();
    }

    @Override
    public int compareTo(TimestampedEntity o) {
        return this.getId().compareTo(o.getId());
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass() || getId() == null) {
            return false;
        }

        return this.getId().equals(((TimestampedEntity) other).getId());
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}