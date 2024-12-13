package Donatur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.DatabaseConnection;

public class FoodConnection {

    public List<String[]> getFoods(int userId) {
        List<String[]> foodList = new ArrayList<>();
        String query = "SELECT name, portions, imagePath FROM foods WHERE userId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String portions = String.valueOf(rs.getInt("portions"));
                    String imagePath = rs.getString("imagePath");
                    foodList.add(new String[]{name, portions, imagePath});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodList;
    }
}

