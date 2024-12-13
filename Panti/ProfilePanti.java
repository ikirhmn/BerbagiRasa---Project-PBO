package Panti;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.DatabaseConnection;

public class ProfilePanti {
    private int userId;

    public ProfilePanti(int userId) {
        this.userId = userId;

        // Membuat JFrame untuk ProfilePanti
        JFrame frame = new JFrame("Profile Panti");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Membuat HeaderPanel yang sama dengan Beranda
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1440, 80);
        headerPanel.setBackground(new Color(50, 150, 64));
        headerPanel.setLayout(null);

        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);

        JButton berandaButton = createTransparentButton("Beranda");
        berandaButton.setBounds(300, 20, 100, 40);
        headerPanel.add(berandaButton);

        JButton profileButton = new JButton();
        profileButton.setBounds(1250, 10, 60, 60);
        profileButton.setFocusPainted(false);
        profileButton.setBorder(BorderFactory.createEmptyBorder());
        profileButton.setContentAreaFilled(false);
        profileButton.setOpaque(true);
        profileButton.setBackground(Color.WHITE);
        profileButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        profileButton.setFont(new Font("Poppins", Font.BOLD, 20));
        profileButton.setText("P");
        profileButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        headerPanel.add(profileButton);

        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka Frame Riwayat
                new BerandaPanti(userId);
                frame.dispose();
            }
        });

        frame.add(headerPanel);

        // Panel Konten Profile
        JPanel profilePanel = new JPanel();
        profilePanel.setBounds(0, 80, 1440, 800);
        profilePanel.setLayout(null);

        // Contoh Konten Profile
        JLabel profileLabel = new JLabel("<html><b>Profile Panti</b></html>");
        profileLabel.setBounds(20, 20, 400, 40);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 24));
        profilePanel.add(profileLabel);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT nama, alamat FROM Panti WHERE id_panti = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nama = rs.getString("nama");
                        String alamat = rs.getString("alamat");

                        JLabel nameLabel = new JLabel("Nama: " + nama);
                        nameLabel.setBounds(20, 80, 400, 40);
                        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                        profilePanel.add(nameLabel);

                        JLabel addressLabel = new JLabel("Alamat: " + alamat);
                        addressLabel.setBounds(20, 120, 400, 40);
                        addressLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                        profilePanel.add(addressLabel);
                    } else {
                        JLabel errorLabel = new JLabel("Data tidak ditemukan.");
                        errorLabel.setBounds(20, 80, 400, 40);
                        errorLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                        profilePanel.add(errorLabel);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Terjadi kesalahan saat mengambil data.");
            errorLabel.setBounds(20, 80, 400, 40);
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            profilePanel.add(errorLabel);
        }


        // Menambahkan panel ke frame
        frame.add(profilePanel);

        // Menampilkan JFrame ProfilePanti
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    // Fungsi untuk membuat tombol transparan
    private static JButton createTransparentButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
