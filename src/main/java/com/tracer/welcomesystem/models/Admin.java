package com.tracer.welcomesystem.models;


import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Admin")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "password")
    private String password;



    @Column(name = "college")
    private String college;



    public Admin() {

    }


    public Admin(String username, String password) {
        this.name = username;
        this.password = password;
    }
}
