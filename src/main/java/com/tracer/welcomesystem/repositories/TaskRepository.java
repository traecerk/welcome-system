package com.tracer.welcomesystem.repositories;

import com.tracer.welcomesystem.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    default void updateTask(Long id, String name, String description){
        Task task = new Task(id, name, description);
        save(task);
    }
}
