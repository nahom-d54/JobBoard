/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author nahom
 */
public class JobDAO {
    public static void createJob(Job job) throws Exception {
        
        Connection connection;
        String sql = "INSERT INTO JOBS(title, requirements, responsibility, company, deadline, user_id) VALUES(?, ?, ?, ?, ?,?)";
        PreparedStatement statement;
        //try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement(sql);
            
            Date sqldate = Date.valueOf(job.getDeadline());
            
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getRequirements());
            statement.setString(3, job.getResponsibility());
            statement.setString(4, job.getCompany());
            statement.setDate(5, sqldate);
            
            statement.setInt(6, job.getId());
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        /*} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnector.closeConnection(connection);
        }*/
    }

    
    public static Job getJobById(int id) throws Exception{
        Connection connection;
        PreparedStatement statement;
      
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM JOBS where id = ?");
            statement.setInt(1, id);
            Job job = new Job();
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setRequirements(resultSet.getString("requirements"));
                job.setResponsibility(resultSet.getString("responsibility"));
                job.setCompany(resultSet.getString("company"));
                job.setDeadline(resultSet.getDate("deadline").toLocalDate());
                break;
            }
            return job;
            
        // Perform database select operation and return User object
    }
    public static List<Job> getJobsByUserId(int user_id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Job> jobs = new ArrayList<>();
        try{
            
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM JOBS where user_id = ?");
            statement.setInt(1, user_id);
            
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setRequirements(resultSet.getString("requirements"));
                job.setResponsibility(resultSet.getString("responsibility"));
                job.setCompany(resultSet.getString("company"));
                job.setDeadline(resultSet.getDate("deadline").toLocalDate());
                
                jobs.add(job);
            }
        }finally {
            // Close the resources in the reverse order of their creation
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return jobs;
            
    }


    public void updateJob(Job job) throws Exception {
        Connection connection = null;
        
        PreparedStatement statement = null;
        String sql = "UPDATE JOBS set title = ?,requirements  = ?, responsibility = ?, company = ?, deadline = ? WHERE id = ?";
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement(sql);
            
            Date sqldate = Date.valueOf(job.getDeadline());
            
            statement.setString(1, job.getTitle());
            statement.setString(2, job.getRequirements());
            statement.setString(3, job.getResponsibility());
            statement.setString(4, job.getCompany());
            statement.setDate(5, sqldate);
            statement.setInt(5, job.getId());
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
        } finally {
            
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static void deleteJob(int job_id) throws Exception{
        Connection connection = null;   
        PreparedStatement statement = null;
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("DELETE FROM JOBS WHERE id = ?");
            
            statement.setInt(1, job_id);
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public List<Job> getJobsByPage(int pageNumber, int itemsPerPage) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Job> jobs = new ArrayList<>();
        try {
            connection = JDBCConnector.getConnection();
            int offset = (pageNumber - 1) * itemsPerPage;
            statement = connection.prepareStatement("SELECT * FROM JOBS LIMIT ? OFFSET ?");
            statement.setInt(1, itemsPerPage);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                // Set job properties from the result set
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setRequirements(resultSet.getString("requirements"));
                job.setResponsibility(resultSet.getString("responsibility"));
                job.setCompany(resultSet.getString("company"));
                LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                job.setDeadline(deadline);
                
                // Add the job to the list
                if( LocalDate.now().isAfter(deadline)){
                    continue;
                }
                jobs.add(job);
            }
        } finally {
            // Close the resources in the reverse order of their creation
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return jobs;
    }
    
     public static List<Job> searchJob(String q) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Job> jobs = new ArrayList<>();
        try {
            connection = JDBCConnector.getConnection();
            
            statement = connection.prepareStatement("SELECT * FROM JOBS where title LIKE ?");
            statement.setString(1, "%"+q+"%");
            
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                // Set job properties from the result set
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setRequirements(resultSet.getString("requirements"));
                job.setResponsibility(resultSet.getString("responsibility"));
                job.setCompany(resultSet.getString("company"));
                LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                job.setDeadline(deadline);
                
                // Add the job to the list
                if( LocalDate.now().isAfter(deadline)){
                    continue;
                }
                jobs.add(job);
            }
        } finally {
            // Close the resources in the reverse order of their creation
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return jobs;
    }
    
}
