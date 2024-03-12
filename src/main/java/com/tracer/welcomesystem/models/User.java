package com.tracer.welcomesystem.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Entity
@Data
@Table(name = "Student")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;


    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User() {

    }
}
