package Donatur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import src.DatabaseConnection;
import src.Makanan;

public class FoodManager {

    // Metode untuk mendapatkan daftar makanan dengan status "ACC"
    public static ArrayList<String[]> getApprovedFoodList(int userId) {
        ArrayList<String[]> foodList = new ArrayList<>();
        String query = """
            SELECT m.photo_path, m.nama, m.waktu_ketersediaan, p.nama AS nama_panti, m.porsi
            FROM makanan m
            JOIN panti p ON m.id_panti = p.id_panti
            WHERE m.status = 'ACC' AND m.id_donatur = ?
        """;
    
        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                String photoPath = rs.getString("photo_path");
                String namaMakanan = rs.getString("nama");
                String waktu = rs.getString("waktu_ketersediaan");
                String namaPanti = rs.getString("nama_panti");
                String porsi = rs.getString("porsi");
                foodList.add(new String[]{photoPath, namaMakanan, waktu, namaPanti, porsi});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodList;
    }
    

    // Metode untuk debug: menampilkan daftar makanan yang di-ACC ke konsol
    public static void printApprovedFoodList(ArrayList<String[]> foodList) {
        if (foodList.isEmpty()) {
            System.out.println("Tidak ada makanan dengan status 'ACC'.");
        } else {
            for (String[] food : foodList) {
                System.out.println("Makanan: [Foto: " + food[0] + ", Nama: " + food[1] +
                                   ", Waktu: " + food[2] + ", Panti: " + food[3] + ", Porsi: " + food[4] + "]");
            }
        }
    }


    public static Makanan getFoodById(int idPermintaan) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFoodById'");
    }
}
