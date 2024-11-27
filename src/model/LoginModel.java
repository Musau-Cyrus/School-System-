package model;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

    public boolean validateCredentials(String role, String username, String password) {
        String query = "SELECT * FROM login WHERE Role = ? AND Username = ? AND Password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, role);
            statement.setString(2, username);
            statement.setString(3, password);

            try (ResultSet rs = statement.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}