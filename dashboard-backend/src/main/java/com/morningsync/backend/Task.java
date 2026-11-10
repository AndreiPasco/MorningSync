package com.morningsync.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;
    private LocalDateTime deadline;

    protected Task(){

    }

    public Task(String title, boolean completed, LocalDateTime deadline){
        this.completed = completed;
        this.title = title;
        this.deadline = deadline;
    }

    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void changeStatus(boolean completed){
        this.completed = completed;
    }

    public LocalDateTime getDeadline(){
        return deadline;
    }
}
