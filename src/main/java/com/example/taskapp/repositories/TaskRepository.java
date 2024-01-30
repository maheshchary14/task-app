package com.example.taskapp.repositories;

import com.example.taskapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);

    @Modifying
    @Query("UPDATE Task t SET t.completed= :completed WHERE t.id= :taskId")
    int updateTaskStatus(@Param("taskId") Long taskId, @Param("completed") boolean completed);
}
