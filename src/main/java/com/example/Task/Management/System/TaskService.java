package com.example.Task.Management.System;

import com.example.Task.Management.System.Priority;
import com.example.Task.Management.System.Status;
import com.example.Task.Management.System.Task;

import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
    List<Task> getTasksByStatus(Status status);
    List<Task> getTasksByPriority(Priority priority);
    List<Task> getTasksSortedByDueDate(String order);
    List<Task> getTasksByStatusAndPriority(Status status, Priority priority);
    List<Task> getTasksSortedByPriorityAsc();
    List<Task> getTasksSortedByPriorityDesc();
}