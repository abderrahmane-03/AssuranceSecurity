package com.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long userId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    @Column(length = 500) // Adjust length as needed
    private String actions;

    // Constructors, getters, and setters
    public UserActivity() {}

    public UserActivity(Long userId, LocalDateTime loginTime) {
        this.userId = userId;
        this.loginTime = loginTime;
    }

    public synchronized void addAction(String action) {
        if (this.actions == null) {
            this.actions = action;
        } else {
            this.actions += ", " + action;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalDateTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    // Getters and setters
}
