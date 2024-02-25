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
public class JDBCConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jobboard";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "password";

    public static Connection getConnection() throws SQLException, Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle the exception
            }
        }
    }
}
