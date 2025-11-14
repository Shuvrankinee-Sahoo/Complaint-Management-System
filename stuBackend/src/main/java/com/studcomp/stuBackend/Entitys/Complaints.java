package com.studcomp.stuBackend.Entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Complaints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mail;
    private String title;
    private String subject;
    private String description;
    private String status;
    private String date;
    @Column(length = 1000)
    private String response;
}
