package com.tracer.welcomesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracer.welcomesystem.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);
}