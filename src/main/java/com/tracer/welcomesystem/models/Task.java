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

    @Column(name = "task_status")
    int taskStatus;

    @Column(name = "task_type")
    int taskType;

    @Column(name = "task_priority")
    int taskPriority;

    @ManyToOne
    User user;

    @ManyToOne
    Admin admin;

    public Task(Long id, String taskName, String taskDescription, int taskStatus, int taskType, int taskPriority, User user, Admin admin) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskType = taskType;
        this.taskPriority = taskPriority;
        this.user = user;
        this.admin = admin;
    }

    public Task() {

    }


    public Task(Long id, String name, String description) {
        this.id = id;
        this.taskName = name;
        this.taskDescription = description;
    }
}
