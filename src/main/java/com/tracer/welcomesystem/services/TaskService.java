package com.tracer.welcomesystem.services;

import com.tracer.welcomesystem.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void updateTask(Long id, String name, String description) {
        taskRepository.updateTask(id, name, description);
    }
}
