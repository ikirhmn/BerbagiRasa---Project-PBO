package Donatur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.DatabaseConnection;

public class DetailMakanan extends JDialog {

    public DetailMakanan(JFrame parent, int makananId) {
        super(parent, "Detail Makanan", true);
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel Foto Makanan (Kiri)
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BorderLayout());
        panelKiri.setBackground(Color.WHITE);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

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

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT m.nama, m.porsi, m.photo_path, m.waktu_ketersediaan, m.status, p.nama AS nama_panti, p.alamat AS alamat_panti " +
                    "FROM Makanan m " +
                    "LEFT JOIN Panti p ON m.id_panti = p.id_panti " +
                    "WHERE m.id_makanan = ?";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, makananId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lblNamaMakananValue.setText(rs.getString("nama"));
                lblPorsiValue.setText(String.valueOf(rs.getInt("porsi")));
                lblWaktuValue.setText(rs.getString("waktu_ketersediaan"));
                lblStatusValue.setText(rs.getString("status"));
                lblNamaPantiValue.setText(rs.getString("nama_panti"));
                lblAlamatPantiValue.setText(rs.getString("alamat_panti"));

                String photoPath = rs.getString("photo_path");
                if (photoPath != null && !photoPath.isEmpty()) {
                    imageLabel.setIcon(new ImageIcon(new ImageIcon(photoPath).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
                } else {
                    imageLabel.setText("Tidak ada gambar");
                }

                if ("ACC".equalsIgnoreCase(rs.getString("status"))) {
                    btnACC.setEnabled(false);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Data makanan tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengambil data dari database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        btnACC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String updateQuery = "UPDATE Makanan SET status = 'ACC' WHERE id_makanan = ?";
                    PreparedStatement stmt = conn.prepareStatement(updateQuery);
                    stmt.setInt(1, makananId);

                    int rowsUpdated = stmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        lblStatusValue.setText("ACC");
                        btnACC.setEnabled(false);
                        JOptionPane.showMessageDialog(DetailMakanan.this, "Status berhasil diperbarui.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(DetailMakanan.this, "Terjadi kesalahan saat memperbarui status.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
