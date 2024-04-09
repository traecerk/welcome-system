package com.tracer.welcomesystem.repositories;

import com.tracer.welcomesystem.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    List<Task> findAll();

    Task save(Task task);

    Task findByTaskName(String taskName);
}

