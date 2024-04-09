package com.tracer.welcomesystem.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stuid")
    private String stuid;

    @Column(name = "id_card")
    private String id_card;

    @JsonIgnore
    @Column(name = "passwd")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

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




    public User(String stuid ,String name,String email){
        this.stuid = stuid;
        this.username = name;
        this.email = email;

    }
    public User() {

    }
}
