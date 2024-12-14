package src;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRequestConnection {

    public boolean addRequest(int idPanti, int idMakanan) {
        String query = "INSERT INTO Permintaan (id_panti, id_makanan, tanggal_permintaan, status) VALUES (?, ?, NOW(), 'Belum ACC')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPanti);
            stmt.setInt(2, idMakanan);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String[]> getRequestsByPanti(int idPanti) {
        List<String[]> requestList = new ArrayList<>();
        String query = "SELECT perm.id_permintaan, m.nama AS nama_makanan, m.porsi, perm.status, perm.tanggal_permintaan "
                     + "FROM Permintaan perm "
                     + "JOIN Makanan m ON perm.id_makanan = m.id_makanan "
                     + "WHERE perm.id_panti = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPanti);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idPermintaan = String.valueOf(rs.getInt("id_permintaan"));
                    String namaMakanan = rs.getString("nama_makanan");
                    String porsi = String.valueOf(rs.getInt("porsi"));
                    String status = rs.getString("status");
                    String tanggalPermintaan = rs.getString("tanggal_permintaan");

                    requestList.add(new String[]{idPermintaan, namaMakanan, porsi, status, tanggalPermintaan});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestList;
    }

    public boolean updateRequestStatus(int id_makanan) {
        String query = "UPDATE makanan SET status = 'ACC' WHERE id_makanan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_makanan);
            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;  // Jika ada baris yang diupdate, return true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Jika terjadi kesalahan, return false
        }
    }

      // Method untuk mendapatkan permintaan yang telah di-ACC
    public ArrayList<String[]> getApprovedFoodList(int idPanti) {
        ArrayList<String[]> approvedFoodList = new ArrayList<>();
        String query = "SELECT perm.id_permintaan, m.nama AS nama_makanan, m.porsi, perm.status, perm.tanggal_permintaan "
                     + "FROM permintaan perm "
                     + "JOIN makanan m ON perm.id_makanan = m.id_makanan "
                     + "WHERE perm.id_panti = ? AND perm.status = 'ACC'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPanti);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String idPermintaan = String.valueOf(rs.getInt("id_permintaan"));
                    String namaMakanan = rs.getString("nama_makanan");
                    String porsi = String.valueOf(rs.getInt("porsi"));
                    String status = rs.getString("status");
                    String tanggalPermintaan = rs.getString("tanggal_permintaan");

                    approvedFoodList.add(new String[]{idPermintaan, namaMakanan, porsi, status, tanggalPermintaan});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return approvedFoodList;
    }
}
