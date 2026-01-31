package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserService {

    // SECURITY ISSUE: Hardcoded credentials
    private String password = "admin123";

    // FIXED: SQL Injection prevented using PreparedStatement
    public void findUser(String username) throws java.sql.SQLException {

        String query = "SELECT id, name, email FROM users WHERE name = ?";

        try (Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost/db",
                        "root", password);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.executeQuery();
        }
    }

    public void deleteUser(String username) throws java.sql.SQLException { 

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = 
                DriverManager.getConnection("jdbc:mysql://localhost/db", 
                    "root", password);
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, username);
            pst.execute();
        }
    } 
}