package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet1 extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un = request.getParameter("username");
        PrintWriter out = response.getWriter();
        out.print("<h1>Hello "+ un + "</h1>");
        
        // JDBC Connection parameters
        String url = "jdbc:mysql://localhost:3306/Gangadhar";
        String username = "root";
        String password = "Gangadhar@1";
        Connection connection = null;
        PreparedStatement statement = null;
        try {  
            Class.forName("com.mysql.cj.jdbc.Driver");  // Load the JDBC Driver
            connection = DriverManager.getConnection(url, username, password); // Establishing a database connection
            String sql = "INSERT INTO detail (Name) VALUES (?)"; // SQL INSERT statement
            statement = connection.prepareStatement(sql);
            statement.setString(1, request.getParameter("username")); // Set parameter value
            int rowsAffected = statement.executeUpdate(); // Execute the INSERT statement
        } catch(SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are released
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
