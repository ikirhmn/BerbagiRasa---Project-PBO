package Donatur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.DatabaseConnection;
import src.FoodRequestConnection;

public class DetailMakanan extends JDialog {
    private int id_makanan;
    private int userId;

    public DetailMakanan(JFrame parent, int id_makanan) {
        this.id_makanan = id_makanan;
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel Foto Makanan (Kiri)
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BorderLayout());
        panelKiri.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelKiri.add(imageLabel, BorderLayout.CENTER);

        // Panel Detail Makanan (Kanan)
        JPanel panelDetail = new JPanel();
        panelDetail.setLayout(new GridLayout(6, 2, 10, 10));
        panelDetail.setBackground(Color.WHITE);
        panelDetail.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding di sekitar panel

        JLabel lblNamaMakanan = new JLabel("Nama Makanan:");
        JLabel lblNamaMakananValue = new JLabel();
        panelDetail.add(lblNamaMakanan);
        panelDetail.add(lblNamaMakananValue);

        JLabel lblPorsi = new JLabel("Jumlah Porsi:");
        JLabel lblPorsiValue = new JLabel();
        panelDetail.add(lblPorsi);
        panelDetail.add(lblPorsiValue);

        JLabel lblWaktu = new JLabel("Waktu Kesediaan:");
        JLabel lblWaktuValue = new JLabel();
        panelDetail.add(lblWaktu);
        panelDetail.add(lblWaktuValue);

        JLabel lblStatus = new JLabel("Status:");
        JLabel lblStatusValue = new JLabel();
        panelDetail.add(lblStatus);
        panelDetail.add(lblStatusValue);

        JLabel lblNamaPanti = new JLabel("Nama Panti:");
        JLabel lblNamaPantiValue = new JLabel();
        panelDetail.add(lblNamaPanti);
        panelDetail.add(lblNamaPantiValue);

        JLabel lblAlamatPanti = new JLabel("Alamat Panti:");
        JLabel lblAlamatPantiValue = new JLabel();
        panelDetail.add(lblAlamatPanti);
        panelDetail.add(lblAlamatPantiValue);

        JButton btnACC = new JButton("ACC");
        btnACC.setBackground(new Color(82, 170, 94));
        btnACC.setForeground(Color.WHITE);
        btnACC.setFont(new Font("Arial", Font.BOLD, 16));
        btnACC.setFocusPainted(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnACC);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(panelKiri, BorderLayout.WEST);
        contentPanel.add(panelDetail, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Ambil data makanan dari database
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Koneksi berhasil!");
            } else {
                System.out.println("Koneksi gagal.");
            }

            String query = """
                    SELECT m.id_makanan, m.nama, m.porsi, m.photo_path, m.waktu_ketersediaan, m.status, p.nama_panti, p.alamat_panti
                    FROM makanan m
                    LEFT JOIN permintaan pm ON m.id_makanan = pm.id_makanan
                    LEFT JOIN panti p ON pm.id_panti = p.id_panti
                    WHERE m.id_makanan = ?
                           """;

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id_makanan);
            System.out.println("Makanan ID yang diteruskan: " + id_makanan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lblNamaMakananValue.setText(rs.getString("nama"));
                lblPorsiValue.setText(String.valueOf(rs.getInt("porsi")));
                lblWaktuValue.setText(rs.getString("waktu_ketersediaan"));
                lblStatusValue.setText(rs.getString("status"));
                lblNamaPantiValue
                        .setText(rs.getString("nama_panti") != null ? rs.getString("nama_panti") : "Belum ada");
                lblAlamatPantiValue
                        .setText(rs.getString("alamat_panti") != null ? rs.getString("alamat_panti") : "Belum ada");

                // Tampilkan gambar jika ada
                String photoPath = rs.getString("photo_path");
                if (photoPath != null && !photoPath.isEmpty()) {
                    File imageFile = new File(photoPath);
                    if (imageFile.exists()) {
                        imageLabel.setIcon(new ImageIcon(new ImageIcon(photoPath).getImage()
                                .getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                    } else {
                        imageLabel.setText("Gambar tidak ditemukan");
                    }
                } else {
                    imageLabel.setText("Tidak ada gambar");
                }

                // Matikan tombol jika status sudah ACC
                if ("ACC".equalsIgnoreCase(rs.getString("status"))) {
                    btnACC.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data makanan tidak ditemukan.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                dispose();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Terjadi kesalahan saat mengambil data dari database: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        btnACC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    if (conn != null) {
                        conn.setAutoCommit(false);

                        // Update status menjadi 'ACC'
                        String updateQuery = "UPDATE makanan SET status = 'ACC' WHERE id_makanan = ?";
                        PreparedStatement stmtUpdate = conn.prepareStatement(updateQuery);
                        stmtUpdate.setInt(1, id_makanan);
                        stmtUpdate.executeUpdate();

                        // Insert data ke tabel permintaan
                        String insertQuery = """
                                INSERT INTO permintaan (id_panti, id_makanan, tanggal_permintaan, status, tanggal_acc)
                                SELECT id_panti, id_makanan, CURRENT_TIMESTAMP(), 'ACC', CURRENT_TIMESTAMP()
                                FROM makanan WHERE id_makanan = ? AND status = 'ACC'""";
                        PreparedStatement stmtInsert = conn.prepareStatement(insertQuery);
                        stmtInsert.setInt(1, id_makanan);
                        stmtInsert.executeUpdate();

                        conn.commit();

                        JOptionPane.showMessageDialog(null, "Makanan berhasil di-ACC!");

                        // Memanggil metode refreshData() di RiwayatDonatur
                        // Pastikan 'userId' adalah ID user yang relevan
                        lblStatusValue.setText("ACC"); // Menampilkan status 'ACC' setelah tombol ditekan
                        btnACC.setEnabled(false);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat ACC makanan.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
