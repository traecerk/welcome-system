package com.tracer.welcomesystem.repositories;

import com.tracer.welcomesystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tracer.welcomesystem.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

    Admin findByName(String name);

}