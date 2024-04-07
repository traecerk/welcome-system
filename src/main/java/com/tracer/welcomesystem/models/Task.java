package com.tracer.welcomesystem.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


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

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date startTime;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date endTime;


    public Task() {

    }


    public Task(String taskName, String taskDescription, String college, int Optional, int taskPriority, int taskDependency, int ad, int online, String location, java.util.Date startTime, java.util.Date endTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.college = college;
        this.Optional = Optional;
        this.taskPriority = taskPriority;
        this.taskDependency = taskDependency;
        this.ad = ad;
        this.online = online;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
