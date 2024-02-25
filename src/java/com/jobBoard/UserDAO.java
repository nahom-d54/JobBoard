/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jobBoard;
import java.sql.*;
/**
 *
 * @author nahom
 */
public class UserDAO {
    public void createUser(User user) throws Exception {
        Connection connection;
        String password = PasswordOprations.hashPassword(user.getPassword());
        
        PreparedStatement statement;
        //try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("INSERT INTO USERS(username, email, password) VALUES(?, ?, ?)");
            
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, password);
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        /*} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnector.closeConnection(connection);
        }*/
    }

    
    public User getUserById(int id) throws Exception{
        Connection connection;
        PreparedStatement statement;
      
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM USERS where id = ?");
            statement.setInt(1, id);
            User user = new User();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;
            
        // Perform database select operation and return User object
    }
    
    public User getUserByUsername(String username) throws Exception{
        Connection connection;
        PreparedStatement statement;
      
        connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("SELECT * FROM USERS where username = ?");
            statement.setString(1, username);
            User user = new User();
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
            }
            return user;
            
        // Perform database select operation and return User object
    }

    public static void updateUser(User user) {
        Connection connection = null;
        String password = PasswordOprations.hashPassword(user.getPassword());
        
        PreparedStatement statement;
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("UPDATE USERS set username = ?, email = ?, password  = ? name = ? WHERE id = ?");
            
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, password);
            statement.setString(3, user.getName());
            statement.setInt(5, user.getId());
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
        } finally {
            JDBCConnector.closeConnection(connection);
        }
    }

    public static void deleteUser(int id) {
        Connection connection = null;   
        PreparedStatement statement;
        try {
            connection = JDBCConnector.getConnection();
            statement = connection.prepareStatement("DELETE FROM USERS WHERE id = ?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            // Perform database insert operation using the connection and prepared statement
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCConnector.closeConnection(connection);
        }
    }

    // Other DAO methods
}
