package Donatur;

import javax.swing.*;

import src.DatabaseConnection;
import src.Makanan;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BerandaDonatur {
    private int userId;

    public BerandaDonatur(int userId) {
        this.userId = userId;
        JFrame frame = new JFrame("Beranda Aplikasi");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Membuat HeaderPanel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1440, 80);
        headerPanel.setBackground(new Color(82, 170, 94));
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
                // Membuka Frame Riwayat
                new RiwayatDonatur(userId);
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

        frame.add(headerPanel);

        // Membuat BannerPanel
        JPanel bannerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Menggambar gambar latar di panel
                ImageIcon bannerImage = new ImageIcon("assets\\banner beranda.jpg");
                g.drawImage(bannerImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        bannerPanel.setBounds(0, 80, 1440, 300); // Di bawah HeaderPanel
        bannerPanel.setLayout(null);

        // Slogan di atas gambar
        JLabel sloganLabel = new JLabel(
                "<html><div style='text-align: left;'><b>Bagikan surplus makananmu<br>untuk membantu panti asuhan yang membutuhkan.</b></div></html>");
        sloganLabel.setBounds(20, 150, 1440, 100); // Posisi dan ukuran slogan
        sloganLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setHorizontalAlignment(SwingConstants.LEFT);

        bannerPanel.add(sloganLabel);
        frame.add(bannerPanel);

        // Panel Daftar Makanan
        JPanel foodPanel = new JPanel();
        foodPanel.setBounds(0, 380, 1440, 400);
        foodPanel.setLayout(null);

        // Judul Daftar Makanan
        JLabel foodTitle = new JLabel("Daftar Makanan");
        foodTitle.setBounds(20, 20, 400, 40);
        foodTitle.setFont(new Font("Arial", Font.BOLD, 24));
        foodPanel.add(foodTitle);

        // Tombol "Tambah Donasi"
        JButton tambahDonasiButton = new JButton("Tambah Donasi");
        tambahDonasiButton.setBounds(1130, 20, 180, 40);
        tambahDonasiButton.setBackground(new Color(55, 156, 199));
        tambahDonasiButton.setForeground(Color.WHITE);
        tambahDonasiButton.setFont(new Font("Arial", Font.BOLD, 14));
        tambahDonasiButton.setBorderPainted(false); // Tidak ada border pada tombol
        foodPanel.add(tambahDonasiButton);

        // Panel untuk kartu makanan (menggunakan ScrollPane)
        JScrollPane foodScrollPane = new JScrollPane();
        foodScrollPane.setBorder(BorderFactory.createEmptyBorder());
        foodScrollPane.setBounds(10, 60, 1400, 300);
        foodScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        foodScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel foodCardPanel = new JPanel();
        foodCardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        foodCardPanel.setBorder(BorderFactory.createEmptyBorder());

        // Mengambil data makanan dari database
        FoodConnection foodConnection = new FoodConnection();
        List<String[]> foodDataList = foodConnection.getFoods(userId); // Gantilah 1 dengan userId yang sesuai

        // Membuat kartu makanan berdasarkan data yang diambil dari database
        for (String[] foodData : foodDataList) {
            if (foodData.length < 4) continue; // Lewati jika data tidak lengkap

            // Ambil data dari foodList
            int idMakanan = Integer.parseInt(foodData[0]);
            String nama = foodData[1];
            String porsi = foodData[2];
            String photoPath = foodData[3];

            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(200, 230));
            card.setLayout(null);
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            // Foto makanan
            JLabel foodImage = new JLabel();
            foodImage.setBounds(10, 10, 180, 120);
            foodImage.setIcon(new ImageIcon(
                    new ImageIcon(photoPath).getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH)));
            card.add(foodImage);

            // Tombol Req (disabled)
            JButton reqButton = new JButton("Req");
            reqButton.setBounds(10, 140, 180, 30);
            reqButton.setEnabled(false);
            reqButton.setBackground(Color.LIGHT_GRAY);
            reqButton.setForeground(Color.WHITE);
            reqButton.setFont(new Font("Arial", Font.BOLD, 14));
            reqButton.setBorderPainted(false);
            card.add(reqButton);

            // Label nama makanan
            JLabel foodName = new JLabel(nama, SwingConstants.CENTER);
            foodName.setBounds(10, 180, 180, 20);
            foodName.setFont(new Font("Arial", Font.PLAIN, 16));
            foodName.setHorizontalAlignment(SwingConstants.LEFT);
            card.add(foodName);

            // Label porsi
            JLabel foodPortion = new JLabel("Porsi: " + porsi, SwingConstants.CENTER);
            foodPortion.setBounds(10, 210, 180, 20);
            foodPortion.setFont(new Font("Arial", Font.PLAIN, 14));
            foodPortion.setHorizontalAlignment(SwingConstants.LEFT);
            card.add(foodPortion);

            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    try {
                        int idMakanan = Integer.parseInt(foodData[0]); 
                        DetailMakanan detailFrame = new DetailMakanan(frame, idMakanan);
                        detailFrame.setVisible(true);

                        detailFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent e) {
                                frame.setVisible(true); 
                            }
                        });
                    } catch (NumberFormatException e) {
                        String namaMakanan = foodData[1]; 
                        int idMakanan = cariIdMakanan(namaMakanan);

                        if (idMakanan != 0) {
                            DetailMakanan detailFrame = new DetailMakanan(frame, idMakanan);
                            detailFrame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Makanan tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });

            foodCardPanel.add(card);
        }

        foodScrollPane.setViewportView(foodCardPanel);
        foodPanel.add(foodScrollPane);

        frame.add(foodPanel);

        // Menampilkan JFrame
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

     private int cariIdMakanan(String namaMakanan) {
        int id = 0;
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id_makanan FROM makanan WHERE nama = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, namaMakanan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_makanan");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public static void main(String[] args) {
        // Contoh penggunaannya, ganti userId dengan ID pengguna yang sesuai
        new BerandaDonatur(1);
    }
}