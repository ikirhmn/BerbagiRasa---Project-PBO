package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginConnection {
    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?"; 
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
                stmt.setString(1, username);
                stmt.setString(2, password);
            
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); 
                }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
