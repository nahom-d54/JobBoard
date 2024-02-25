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
public class JobApplication {
    private int id;
    private Job job_id;
    private String application_letter;
    private User job_seeker;
    private LocalDate application_date;

    public JobApplication(int id, Job job_id, String application_letter, User job_seeker) {
        this.id = id;
        this.job_id = job_id;
        this.application_letter = application_letter;
        this.job_seeker = job_seeker;
    }

    public JobApplication() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Job getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        try{
        Job job;
        JobDAO jobDao = new JobDAO();
        job = jobDao.getJobById(job_id);
        this.job_id = job;
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }

    public String getApplication_letter() {
        return application_letter;
    }

    public void setApplication_letter(String application_letter) {
        this.application_letter = application_letter;
    }

    public User getJob_seeker() {
        return job_seeker;
    }

    public void setJob_seeker(int job_seeker) {
        User user;
        UserDAO userDao = new UserDAO();
        try{
            user = userDao.getUserById(job_seeker);
            this.job_seeker = user;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

    public LocalDate getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDate application_date) {
        this.application_date = application_date;
    }
    
    
    
}
