package com.studcomp.stuBackend.Entitys;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    private String username;

    private String password;

    private String role;

    @Column(length = 10, nullable = false, unique = true)
    private String regiNo;
}
