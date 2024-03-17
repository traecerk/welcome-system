package com.tracer.welcomesystem.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passwd")
    private String password;


    @Column(name = "username")
    private String username;

    @Column(name = "gender")
    private int gender = 0;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel = "未填写";

    @Column(name = "college")
    private String college = "未填写";

    @Column(name = "major")
    private String major = "未填写";


    public User(Long id, String name,String email){
        this.id = id;
        this.username = name;
        this.email = email;

    }
    public User() {

    }
}
