package com.example.Task.Management.System;

import com.example.Task.Management.System.Priority;
import com.example.Task.Management.System.Status;
import com.example.Task.Management.System.Task;
import com.example.Task.Management.System.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setDueDate(taskDetails.getDueDate());
            task.setPriority(taskDetails.getPriority());
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> getTasksSortedByDueDate(String order) {
        if ("desc".equalsIgnoreCase(order)) {
            return taskRepository.findAllOrderByDueDateDesc();
        }
        return taskRepository.findAllOrderByDueDateAsc();
    }

    @Override
    public List<Task> getTasksByStatusAndPriority(Status status, Priority priority) {
        return taskRepository.findByStatusAndPriority(status, priority);
    }
    // Add these implementations to your TaskServiceImpl class
    @Override
    public List<Task> getTasksSortedByPriorityAsc() {
        return taskRepository.findAllOrderByPriorityAsc();
    }

    @Override
    public List<Task> getTasksSortedByPriorityDesc() {
        return taskRepository.findAllOrderByPriorityDesc();
    }
}