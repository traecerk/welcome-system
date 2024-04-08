package com.tracer.welcomesystem.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date time;

    @Column(name = "college")
    private String college = "ALL";

    public Announcement() {

    }

    public Announcement(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
