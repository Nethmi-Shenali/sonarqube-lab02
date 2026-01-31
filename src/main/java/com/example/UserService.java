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

public void findUser(String username) throws SQLException {

    // Only fetch necessary fields
    String query = "SELECT id, name, email FROM users WHERE name = ?";

    try (Connection conn =
             DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, username);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                // Process user data as needed
            }
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
