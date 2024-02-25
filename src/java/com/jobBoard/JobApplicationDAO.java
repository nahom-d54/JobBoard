/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nahom
 */
public class JobApplicationDAO {
    public void applyJob(JobApplication jobApplication) throws Exception {
        Connection connection = null;
        
        PreparedStatement statement;
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("INSERT INTO job_application(application_letter, job_seeker, job_id) VALUES(?, ?, ?)");
            
            statement.setString(1, jobApplication.getApplication_letter());
            statement.setInt(2, jobApplication.getJob_seeker().getId());
            statement.setInt(3, jobApplication.getJob_id().getId());
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCConnector.closeConnection(connection);
        }
    }

    /**
     *
     * @param jobApplicationId
     * @return
     * @throws Exception
     */
    public static JobApplication getJobApplicationById(int jobApplicationId) throws Exception{
        Connection connection;
        PreparedStatement statement;
      
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM job_application where id = ?");
            statement.setInt(1, jobApplicationId);
            JobApplication jobApplication = new JobApplication();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                jobApplication.setId(rs.getInt("id"));
                jobApplication.setApplication_letter(rs.getString("application_letter"));
                jobApplication.setJob_id(rs.getInt("job_id"));
                jobApplication.setJob_seeker(rs.getInt("job_seeker"));
                jobApplication.setApplication_date(rs.getDate("application_date").toLocalDate());
            }
            return jobApplication;
            
        // Perform database select operation and return User object
    }

    /**
     *
     * @param employer_id
     * @return
     * @throws Exception
     */
    public static List<JobApplication> getJobApplicationsByEmployerId(int employer_id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<JobApplication> jobApplications = new ArrayList<>();
        try{
            
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM job_application WHERE job_id IN (SELECT id FROM jobs WHERE user_id = ?)");
            statement.setInt(1, employer_id);
            
            rs = statement.executeQuery();
            while(rs.next()){
                JobApplication jobApplication = new JobApplication();
                jobApplication.setId(rs.getInt("id"));
                jobApplication.setApplication_letter(rs.getString("application_letter"));
                jobApplication.setJob_id(rs.getInt("job_id"));
                jobApplication.setJob_seeker(rs.getInt("job_seeker"));
                jobApplication.setApplication_date(rs.getDate("application_date").toLocalDate());
                
                jobApplications.add(jobApplication);
            }
        }finally {
            // Close the resources in the reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return jobApplications;
            
    }
    
    public static List<JobApplication> getJobApplicationsByEmployerId(int employer_id, int job_id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<JobApplication> jobApplications = new ArrayList<>();
        try{
            
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM job_application WHERE job_id IN (SELECT id FROM jobs WHERE user_id = ? AND id = ?)");
            statement.setInt(1, employer_id);
            statement.setInt(2, job_id);
            rs = statement.executeQuery();
            while(rs.next()){
                JobApplication jobApplication = new JobApplication();
                jobApplication.setId(rs.getInt("id"));
                jobApplication.setApplication_letter(rs.getString("application_letter"));
                jobApplication.setJob_id(rs.getInt("job_id"));
                jobApplication.setJob_seeker(rs.getInt("job_seeker"));
                jobApplication.setApplication_date(rs.getDate("application_date").toLocalDate());
                
                jobApplications.add(jobApplication);
            }
        }finally {
            // Close the resources in the reverse order of their creation
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return jobApplications;
            
    }
    
    public void updateJobApplication(JobApplication jobApplication) throws Exception {
        Connection connection = null;
        
        PreparedStatement statement;
        String sql = "UPDATE job_application set application_letter = ? WHERE id = ?";
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, jobApplication.getApplication_letter());
            statement.setInt(2, jobApplication.getId());
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            JDBCConnector.closeConnection(connection);
        }
    }
    

}
