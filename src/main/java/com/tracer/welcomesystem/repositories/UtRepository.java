package com.tracer.welcomesystem.repositories;

import com.tracer.welcomesystem.models.UserTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtRepository extends JpaRepository<UserTasks, Long> {

    List<UserTasks> findAll();


    List<UserTasks> findByUserId(Long id);

    List<UserTasks> findAllByTaskId(Long id);
}