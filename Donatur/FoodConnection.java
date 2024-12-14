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
        String query = "SELECT id_makanan, nama, porsi, photo_path FROM makanan WHERE id_donatur = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set parameter untuk query
            stmt.setInt(1, idDonatur);

            // Eksekusi query dan baca hasilnya
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                String idMakanan = String.valueOf(rs.getInt("id_makanan"));
                String nama = rs.getString("nama");
                String porsi = String.valueOf(rs.getInt("porsi"));
                String photoPath = rs.getString("photo_path");
                   // Log data yang ditambahkan ke foodList
                System.out.println("Data makanan: " + idMakanan + ", " + nama + ", " + porsi + ", " + photoPath);

                foodList.add(new String[]{idMakanan, nama, porsi, photoPath});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return foodList;
}
}