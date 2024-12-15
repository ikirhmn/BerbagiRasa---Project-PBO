package Donatur;

import javax.swing.*;

import src.DatabaseConnection;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiwayatDonatur {
        private int userId;

        public RiwayatDonatur(int userId) {
                this.userId = userId;

                // Membuat JFrame untuk Riwayat
                JFrame frame = new JFrame("Riwayat Donasi");
                frame.setSize(1440, 900);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // Panel Utama (Root Panel)
                JPanel rootPanel = new JPanel();
                rootPanel.setLayout(new BorderLayout());

                // Header Panel
                JPanel headerPanel = new JPanel();
                headerPanel.setPreferredSize(new Dimension(1440, 80));
                headerPanel.setBackground(new Color(82, 170, 94));
                headerPanel.setLayout(null);

                // Menambahkan Label Aplikasi
                JLabel appName = new JLabel("BerbagiRasa");
                appName.setBounds(20, 20, 300, 40);
                appName.setFont(new Font("Poppins", Font.BOLD, 34));
                appName.setForeground(Color.WHITE);
                headerPanel.add(appName);

                // Tombol Navigasi
                JButton berandaButton = createTransparentButton("Beranda");
                berandaButton.setBounds(320, 20, 100, 40);
                berandaButton.setFont(new Font("Poppins", Font.PLAIN, 16));
                headerPanel.add(berandaButton);

                JButton donasiButton = createTransparentButton("Donasi");
                donasiButton.setBounds(440, 20, 100, 40);
                donasiButton.setFont(new Font("Poppins", Font.PLAIN, 16));
                headerPanel.add(donasiButton);

                JButton riwayatButton = createTransparentButton("Riwayat");
                riwayatButton.setBounds(560, 20, 100, 40);
                riwayatButton.setFont(new Font("Poppins", Font.BOLD, 16));
                headerPanel.add(riwayatButton);

                // Tombol Profil
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

                // Menambahkan HeaderPanel ke Frame
                rootPanel.add(headerPanel, BorderLayout.NORTH);

                // Button Riwayat
                berandaButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Membuka Frame Donasi
                            new BerandaDonatur(userId);
                            frame.dispose(); // Menutup JFrame utama jika diperlukan
                        }
                    });
                    
                // Button Donasi
                donasiButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // Membuka Frame Donasi
                                new Donasi(userId);
                                frame.dispose(); // Menutup JFrame utama jika diperlukan
                        }
                });

                // Button Profile
                profileButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // Membuka Frame Profile
                                new ProfileDonatur(userId);
                                frame.dispose(); // Menutup JFrame utama jika diperlukan
                        }
                });

                // Panel Utama
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Gunakan BoxLayout untuk panel
                                                                                       // yang
                                                                                       // scrollable
                contentPanel.setBackground(new Color(245, 245, 245));

                // Label Riwayat
                JLabel riwayatLabel = new JLabel("Riwayat");
                riwayatLabel.setFont(new Font("Poppins", Font.BOLD, 24));
                riwayatLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Pastikan label rata kiri
                contentPanel.add(riwayatLabel);

                // Memuat dan Menampilkan Data Makanan yang Sudah di-ACC
                refreshData(contentPanel);

                // Membungkus contentPanel dalam JScrollPane
                JScrollPane scrollPane = new JScrollPane(contentPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                // Menambahkan scrollPane ke rootPanel
                rootPanel.add(scrollPane, BorderLayout.CENTER);

                // Menambahkan rootPanel ke frame
                frame.add(rootPanel);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
        }

        public void refreshData(JPanel contentPanel) {
                contentPanel.removeAll();

                try (Connection conn = DatabaseConnection.getConnection()) {
                        if (conn != null) {
                                String query = """
                                                SELECT
                                                    m.nama,
                                                    p.nama_panti,
                                                    m.porsi,
                                                    m.photo_path,
                                                    permintaan.tanggal_acc
                                                FROM
                                                    permintaan
                                                JOIN
                                                    makanan m ON permintaan.id_makanan = m.id_makanan
                                                JOIN
                                                    panti p ON permintaan.id_panti = p.id_panti
                                                WHERE
                                                    permintaan.status = 'ACC' AND m.status = 'ACC'
                                                ORDER BY permintaan.tanggal_acc DESC
                                                """;

                                PreparedStatement stmt = conn.prepareStatement(query);
                                ResultSet rs = stmt.executeQuery();

                                boolean dataFound = false;

                                while (rs.next()) {
                                        dataFound = true;
                                        String namaMakanan = rs.getString("nama");
                                        String namaPanti = rs.getString("nama_panti");
                                        String jumlahPorsi = rs.getString("porsi");
                                        String imagePath = rs.getString("photo_path");
                                        String tanggalACC = rs.getString("tanggal_acc");

                                        JPanel card = createCard(imagePath, namaMakanan, tanggalACC, namaPanti,
                                                        jumlahPorsi);
                                        contentPanel.add(card);
                                        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                                }

                                if (!dataFound) {
                                        JLabel noDataLabel = new JLabel("Belum ada riwayat donasi.");
                                        noDataLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
                                        noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        contentPanel.add(noDataLabel);
                                }
                        }
                } catch (SQLException ex) {
                        ex.printStackTrace();
                }

                contentPanel.revalidate();
                contentPanel.repaint();
        }

        // Fungsi untuk membuat card
        private static JPanel createCard(String imagePath, String namaMakanan, String tanggalACC, String namaPanti,
                        String jumlahPorsi) {
                JPanel card = new JPanel();
                card.setLayout(new BorderLayout(10, 10));
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                card.setPreferredSize(new Dimension(1300, 200)); // Ukuran kartu tetap proporsional
                card.setMaximumSize(new Dimension(1300, 200));

                JLabel imageLabel = new JLabel();
                if (imagePath != null && !imagePath.isEmpty()) {
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage()
                                                .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                                imageLabel.setIcon(icon);
                        } else {
                                imageLabel.setText("Gambar tidak ditemukan");
                        }
                } else {
                        imageLabel.setText("No Image");
                }

                // Informasi
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setBackground(Color.WHITE);

                JLabel namaLabel = new JLabel("<html><b>" + namaMakanan + "</b></html>");
                namaLabel.setFont(new Font("Poppins", Font.BOLD, 24));

                JLabel tanggalLabel = new JLabel("Tanggal ACC: " + tanggalACC);
                tanggalLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

                JLabel pantiLabel = new JLabel("Panti Asuhan: " + namaPanti);
                pantiLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

                JLabel porsiLabel = new JLabel("Jumlah Porsi: " + jumlahPorsi);
                porsiLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

                infoPanel.add(namaLabel);
                infoPanel.add(tanggalLabel);
                infoPanel.add(pantiLabel);
                infoPanel.add(porsiLabel);

                // Tombol "Sudah ACC"
                JButton accButton = new JButton("Sudah ACC");
                accButton.setFont(new Font("Poppins", Font.PLAIN, 14));
                accButton.setEnabled(false); // Tombol di-disable
                accButton.setBackground(new Color(200, 200, 200)); // Warna tombol untuk disabled state
                accButton.setForeground(Color.DARK_GRAY);
                accButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                // Menambahkan tombol ke infoPanel
                infoPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Memberi jarak antara info dan tombol
                infoPanel.add(accButton);

                // Menambahkan ke card
                card.add(imageLabel, BorderLayout.WEST);
                card.add(infoPanel, BorderLayout.CENTER);

                return card;
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
