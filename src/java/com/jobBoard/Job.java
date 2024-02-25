/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobBoard;

import java.time.LocalDate;

/**
 *
 * @author nahom
 */
public class Job {
    private String title;
    private String requirements;
    private String responsibility;
    private String company;
    private LocalDate deadline;
    private int id;

    public Job(String title, String requirements, String responsibility, String company, LocalDate date, int id) {
        this.title = title;
        this.requirements = requirements;
        this.responsibility = responsibility;
        this.company = company;
        this.deadline = date;
        this.id = id;
    }
    public Job(){
        
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
