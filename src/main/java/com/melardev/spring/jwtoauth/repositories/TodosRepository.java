package com.melardev.spring.jwtoauth.repositories;


import com.melardev.spring.jwtoauth.entities.Todo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodosRepository extends CrudRepository<Todo, Long> {

    @Query("select new Todo(t.id, t.title, t.completed, t.createdAt, t.updatedAt) from Todo t order by t.createdAt desc")
    List<Todo> findAllHqlSummary();

    List<Todo> findByCompleted(boolean done);

    List<Todo> findByCompletedTrue();

    List<Todo> findByCompletedFalse();

    List<Todo> findByCompletedIsTrue();

    List<Todo> findByCompletedIsFalse();

    List<Todo> findByTitleContains(String title);

    List<Todo> findByDescriptionContains(String description);

    @Query("select new Todo(t.id, t.title, t.completed, t.createdAt, t.updatedAt) from Todo t where t.completed = :completed")
    List<Todo> findByHqlCompletedIs(boolean completed);

    @Query("select t from Todo t where t.title like %:word%")
    List<Todo> findByHqlTitleLike(String word);

    @Query("SELECT t FROM Todo t WHERE title = :title and description  = :description")
    List<Todo> findByHqlTitleAndDescription(String title, String description);

    @Query("select t FROM Todo t WHERE title = ?0 and description  = ?1")
    List<Todo> findByTHqlTitleAndDescription(String title, String description);

    List<Todo> findByCreatedAtAfter(LocalDateTime date);

    List<Todo> findByCreatedAtBefore(LocalDateTime date);

}