package com.tracer.welcomesystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UT")
public class UserTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "status")
    private int status;

}
