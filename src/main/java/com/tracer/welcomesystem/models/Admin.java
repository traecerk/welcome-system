package com.tracer.welcomesystem.models;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "department")
    private String department;

    public Admin(Long id, String name, String email, String password, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.department = department;
    }

    public Admin() {

    }



}
