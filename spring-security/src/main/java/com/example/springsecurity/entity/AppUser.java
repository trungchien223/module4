package com.example.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "user_name") })
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_name", length = 36, nullable = false)
    private String userName;

    @Column(name = "encryted_password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private AppRole appRole;
    }
