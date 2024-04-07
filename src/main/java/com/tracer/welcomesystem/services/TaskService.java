package com.tracer.welcomesystem.services;

import com.tracer.welcomesystem.models.Task;
import com.tracer.welcomesystem.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task saveTask(Task task) {
        Task save = null;
        Optional<Task> taskOptional = taskRepository.findById(task.getId());
        if (taskOptional.isPresent()) {
            Task task1 = taskOptional.get();
            task1.setTaskName(task.getTaskName());
            task1.setTaskDescription(task.getTaskDescription());
            save = taskRepository.save(task1);
        } else {
            save = taskRepository.save(task);
        }
        return save;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }




}





