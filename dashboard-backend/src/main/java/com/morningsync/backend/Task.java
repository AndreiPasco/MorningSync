package com.morningsync.backend;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;

    protected Task(){

    }

    public Task(String title, boolean completed){
        this.completed = completed;
        this.title = title;
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
}
