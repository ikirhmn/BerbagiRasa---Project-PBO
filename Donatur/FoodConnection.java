package Donatur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.DatabaseConnection;

public class FoodConnection {
    public List<String[]> getFoods(int idDonatur) {
        List<String[]> foodList = new ArrayList<>();
        String query = "SELECT nama, porsi, photo_path FROM Makanan WHERE id_donatur = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameter untuk query
            stmt.setInt(1, idDonatur);

            // Eksekusi query dan baca hasilnya
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nama = rs.getString("nama");
                    String porsi = String.valueOf(rs.getInt("porsi"));
                    String photoPath = rs.getString("photo_path");
                    foodList.add(new String[]{nama, porsi, photoPath});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodList;
    }
}