package Donatur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.CircleUser;
import src.DatabaseConnection;
import src.loginas;

public class ProfileDonatur {
    private int userId;

    public ProfileDonatur(int userId) {
        this.userId = userId;

        // Membuat JFrame untuk ProfileDonatur
        JFrame frame = new JFrame("Profile Donatur");
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

        JButton donasiButton = createTransparentButton("Donasi");
        donasiButton.setBounds(420, 20, 100, 40);
        headerPanel.add(donasiButton);

        JButton riwayatButton = createTransparentButton("Riwayat");
        riwayatButton.setBounds(540, 20, 100, 40);
        headerPanel.add(riwayatButton);

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

        // Button Riwayat
        riwayatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RiwayatDonatur(userId);
                frame.dispose();
            }
        });

        // Button Donasi
        donasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Donasi(userId);
                frame.dispose();
            }
        });

        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BerandaDonatur(userId);
                frame.dispose();
            }
        });

        frame.add(headerPanel);

        // Panel Konten Profile
        JPanel profilePanel = new JPanel();
        profilePanel.setBounds(0, 80, 1440, 800);
        profilePanel.setLayout(null);

        JButton logout = new JButton("Keluar");
        logout.setFont(new Font("Poppins", Font.BOLD, 14));
        logout.setBackground(new Color(19, 85, 137));
        logout.setBounds(550, 490, 350, 30);
        logout.setForeground(Color.WHITE);
        profilePanel.add(logout);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginas();
                frame.dispose();
            }
        });

        JLabel profileLabel = new JLabel("<html><b>Profile Donatur</b></html>");
        profileLabel.setBounds(20, 30, 400, 40);
        profileLabel.setFont(new Font("Arial", Font.BOLD, 34));
        profilePanel.add(profileLabel);

        String profilePath = null; // Untuk menyimpan path foto dari database

        // Mengambil data profil dari database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT nama, alamat, profile_path FROM donatur WHERE id_donatur = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String nama = rs.getString("nama");
                        String alamat = rs.getString("alamat");
                        profilePath = rs.getString("profile_path");

                        JLabel nameLabel = new JLabel("Nama: " + nama);
                        nameLabel.setBounds(600, 400, 400, 40);
                        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
                        profilePanel.add(nameLabel);

                        JLabel addressLabel = new JLabel("Alamat: " + alamat);
                        addressLabel.setBounds(600, 440, 400, 40);
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

        // Menampilkan Gambar Profil
        if (profilePath != null && !profilePath.isEmpty()) {
            try {
                ImageIcon profileIcon = new ImageIcon(profilePath);
                Image circularProfileImage = CircleUser.createCircularImage(profileIcon.getImage(), 240);

                JLabel profileImageLabel = new JLabel(new ImageIcon(circularProfileImage));
                profileImageLabel.setBounds(600, 150, 240, 240); // Atur posisi gambar
                profilePanel.add(profileImageLabel);
            } catch (Exception ex) {
                ex.printStackTrace();
                JLabel errorImageLabel = new JLabel("Gagal memuat foto profil.");
                errorImageLabel.setBounds(600, 100, 240, 240);
                profilePanel.add(errorImageLabel);
            }
        } else {
            JLabel noImageLabel = new JLabel("Foto profil belum tersedia.");
            noImageLabel.setBounds(600, 100, 240, 240);
            profilePanel.add(noImageLabel);
        }


        // Menambahkan panel ke frame
        frame.add(profilePanel);

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
