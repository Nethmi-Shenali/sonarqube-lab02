package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // Credentials loaded from environment variables (NO hardcoding)
    private static final String DB_URL =
            "jdbc:mysql://localhost/db";
    private static final String DB_USER =
            System.getenv("DB_USER");
    private static final String DB_PASSWORD =
            System.getenv("DB_PASSWORD");

    // FIXED: SQL Injection + Resource handling
    public void findUser(String username) throws SQLException {

        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn =
                 DriverManager.getConnection(
                     DB_URL, DB_USER, DB_PASSWORD
                 );
             PreparedStatement ps =
                 conn.prepareStatement(query)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                // Process result set if required
            }
        }
    }

    // FIXED: SQL Injection + Resource handling
    public void deleteUser(String username) throws SQLException {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn =
                 DriverManager.getConnection(
                     DB_URL, DB_USER, DB_PASSWORD
                 );
             PreparedStatement ps =
                 conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
