package com.example.Task.Management.System;

import com.example.Task.Management.System.Priority;
import com.example.Task.Management.System.Status;
import com.example.Task.Management.System.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(Priority priority);

    @Query("SELECT t FROM Task t ORDER BY t.dueDate ASC")
    List<Task> findAllOrderByDueDateAsc();

    @Query("SELECT t FROM Task t ORDER BY t.dueDate DESC")
    List<Task> findAllOrderByDueDateDesc();

    @Query("SELECT t FROM Task t WHERE (:status IS NULL OR t.status = :status) AND (:priority IS NULL OR t.priority = :priority)")
    List<Task> findByStatusAndPriority(@Param("status") Status status, @Param("priority") Priority priority);

    @Query("SELECT t FROM Task t ORDER BY t.priority ASC")
    List<Task> findAllOrderByPriorityAsc();

    @Query("SELECT t FROM Task t ORDER BY t.priority DESC")
    List<Task> findAllOrderByPriorityDesc();
}