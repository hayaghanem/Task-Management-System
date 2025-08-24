package com.example.Task.Management.System;

import com.example.Task.Management.System.Priority;
import com.example.Task.Management.System.Status;
import com.example.Task.Management.System.Task;
import com.example.Task.Management.System.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order) {

        if (status != null && priority != null) {
            return taskService.getTasksByStatusAndPriority(status, priority);
        } else if (status != null) {
            return taskService.getTasksByStatus(status);
        } else if (priority != null) {
            return taskService.getTasksByPriority(priority);
        } else if ("dueDate".equals(sortBy)) {
            // Use the order parameter for due date sorting
            if ("desc".equalsIgnoreCase(order)) {
                return taskService.getTasksSortedByDueDate("desc");
            }
            return taskService.getTasksSortedByDueDate("asc");
        } else if ("priority".equals(sortBy)) {
            // Add priority sorting with order parameter
            if ("desc".equalsIgnoreCase(order)) {
                return taskService.getTasksSortedByPriorityDesc();
            }
            return taskService.getTasksSortedByPriorityAsc();
        }

        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}