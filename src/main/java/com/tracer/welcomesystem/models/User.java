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

    @Column(name = "passwd")
    private String password;


    @Column(name = "username")
    private String username;

    @Column(name = "gender")
    private int gender;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "college")
    private String college;

    @Column(name = "major")
    private String major;


    public User(Long id, String name,String email){
        this.id = id;
        this.username = name;
        this.email = email;

    }
    public User() {

    }
}
