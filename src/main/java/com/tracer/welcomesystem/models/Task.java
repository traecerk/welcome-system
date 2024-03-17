package com.tracer.welcomesystem.models;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "college")
    private String college;

    @Column(name = "Optional")
    private int Optional;

    @Column(name = "task_priority")
    private int taskPriority;

    @Column(name = "task_dependency")
    private int taskDependency;

    @Column(name = "ad")
    private int ad;

    @Column(name = "online")
    private int online;

    @Column(name = "location")
    private String location;

    @ManyToOne
    User user;

    @ManyToOne
    Admin admin;


    public Task() {

    }


    public Task(Long id, String name, String description) {
        this.id = id;
        this.taskName = name;
        this.taskDescription = description;
    }
}
