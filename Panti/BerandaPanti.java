package Panti;

import javax.swing.*;

import Donatur.FoodConnection;
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

public class BerandaPanti {
    private int userId;

    public BerandaPanti(int userId) {
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

        // Menambahkan Label Aplikasi
        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);

        // Tombol Navigasi
        JButton berandaButton = createTransparentButton("Beranda");
        berandaButton.setBounds(320, 20, 100, 40);
        berandaButton.setFont(new Font("Poppins", Font.BOLD, 16));
        headerPanel.add(berandaButton);

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
            if (foodData.length < 4)
                continue; // Lewati jika data tidak lengkap

            // Ambil data dari foodList
            int idMakanan = Integer.parseInt(foodData[0]);
            String nama = foodData[1];
            String porsi = foodData[2];
            String photoPath = foodData[3];

            boolean sudahDipesan = isFoodRequested(userId, idMakanan);

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

            // Tombol "Request"
            JButton req = new JButton(sudahDipesan ? "Meminta" : "Request");
            req.setBounds(10, 140, 180, 30);
            req.setBackground(sudahDipesan ? new Color(200, 200, 200) : new Color(55, 156, 199));
            req.setForeground(Color.WHITE);
            req.setFont(new Font("Arial", Font.BOLD, 14));
            req.setBorderPainted(false); // Tidak ada border pada tombol
            req.setEnabled(!sudahDipesan);
            card.add(req);

            // ActionListener untuk tombol req
            req.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Menonaktifkan tombol dan mengubah teks menjadi "Meminta"
                    req.setEnabled(false);
                    req.setText("Meminta");

                    // Menambahkan permintaan ke database
                    try {
                        // Memasukkan data permintaan
                        addFoodRequest(userId, idMakanan);
                        JOptionPane.showMessageDialog(frame, "Permintaan berhasil dikirim!");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "Gagal mengirim permintaan: " + ex.getMessage());
                    }
                }
            });

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

    // Menambahkan permintaan makanan ke database dan memperbarui id_panti di tabel
    // makanan
    private void addFoodRequest(int pantiId, int foodId) throws SQLException {
        String insertRequestSql = "INSERT INTO permintaan (id_panti, id_makanan, tanggal_permintaan) VALUES (?, ?, NOW())";
        String updateMakananSql = "UPDATE makanan SET id_panti = ? WHERE id_makanan = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Mulai transaksi

            // Tambahkan permintaan ke tabel permintaan
            try (PreparedStatement insertStatement = connection.prepareStatement(insertRequestSql)) {
                insertStatement.setInt(1, pantiId);
                insertStatement.setInt(2, foodId);
                insertStatement.executeUpdate();
            }

            // Perbarui kolom id_panti di tabel makanan
            try (PreparedStatement updateStatement = connection.prepareStatement(updateMakananSql)) {
                updateStatement.setInt(1, pantiId);
                updateStatement.setInt(2, foodId);
                updateStatement.executeUpdate();
            }

            connection.commit(); // Selesaikan transaksi
        } catch (SQLException e) {
            throw new SQLException("Gagal memproses permintaan: " + e.getMessage(), e);
        }
    }

    // Fungsi untuk memeriksa apakah panti sudah melakukan permintaan untuk makanan
    // tertentu
    private boolean isFoodRequested(int pantiId, int foodId) {
        String sql = "SELECT COUNT(*) FROM permintaan WHERE id_panti = ? AND id_makanan = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pantiId);
            statement.setInt(2, foodId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Jika sudah ada permintaan, return true
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false; // Jika tidak ada permintaan
    }

}