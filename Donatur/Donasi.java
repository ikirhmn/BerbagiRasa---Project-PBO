package Donatur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.DatabaseConnection;

public class Donasi {
    private int userId;

    public Donasi(int userId) {
        this.userId = userId;

        // Membuat JFrame untuk Donasi
        JFrame frame = new JFrame("Donasi");
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
        berandaButton.setFont(new Font("Poppins", Font.PLAIN, 16));
        headerPanel.add(berandaButton);

        JButton donasiButton = createTransparentButton("Donasi");
        donasiButton.setBounds(440, 20, 100, 40);
        donasiButton.setFont(new Font("Poppins", Font.BOLD, 16));
        headerPanel.add(donasiButton);

        JButton riwayatButton = createTransparentButton("Riwayat");
        riwayatButton.setBounds(560, 20, 100, 40);
        riwayatButton.setFont(new Font("Poppins", Font.PLAIN, 16));
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
        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka Frame Donasi
                new BerandaDonatur(userId);
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
        ;

        // Panel Konten Donasi
        JPanel donasiPanel = new JPanel();
        donasiPanel.setBounds(0, 80, 1440, 800);
        donasiPanel.setLayout(new BorderLayout()); // Menggunakan BorderLayout untuk pembagian kiri dan kanan

        // Panel Kiri (Form Input)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null); // Layout manager untuk form input
        formPanel.setPreferredSize(new Dimension(720, 800)); // Set ukuran panel kiri
        formPanel.setLocation(0, 100); // Menurunkan formPanel 100px dari atas

        // Label dan Input Form di Panel Kiri
        JLabel donasiLabel = new JLabel("<html><b>Form Donasi</b></html>");
        donasiLabel.setBounds(20, 20, 400, 40);
        donasiLabel.setFont(new Font("Arial", Font.BOLD, 34));
        donasiPanel.add(donasiLabel);

        // Input Nama Makanan
        JLabel namaMakananLabel = new JLabel("Nama Makanan:");
        namaMakananLabel.setBounds(20, 80, 200, 30);
        namaMakananLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(namaMakananLabel);

        JTextField namaMakananField = new JTextField();
        namaMakananField.setBounds(220, 80, 400, 30);
        formPanel.add(namaMakananField);

        // Input Porsi
        JLabel jumlahLabel = new JLabel("Porsi:");
        jumlahLabel.setBounds(20, 130, 200, 30);
        jumlahLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(jumlahLabel);

        JTextField porsiField = new JTextField();
        porsiField.setBounds(220, 130, 400, 30);
        formPanel.add(porsiField);

        // Input Waktu Ketersediaan
        JLabel ketersediaanLabel = new JLabel("Waktu Ketersediaan:");
        ketersediaanLabel.setBounds(20, 180, 200, 30);
        ketersediaanLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(ketersediaanLabel);

        JSpinner dateTimeSpinner = new JSpinner(new SpinnerDateModel());
        dateTimeSpinner.setBounds(220, 180, 400, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateTimeSpinner, "yyyy-MM-dd HH:mm:ss");
        dateTimeSpinner.setEditor(dateEditor);
        formPanel.add(dateTimeSpinner);

        // Input Gambar
        JLabel gambarLabel = new JLabel("Gambar Makanan:");
        gambarLabel.setBounds(20, 230, 200, 30);
        gambarLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        formPanel.add(gambarLabel);

        JButton pilihGambarButton = new JButton("Pilih Gambar");
        pilihGambarButton.setBounds(220, 230, 200, 30);
        formPanel.add(pilihGambarButton);

        JLabel filePathLabel = new JLabel("Belum ada file yang dipilih");
        filePathLabel.setBounds(430, 230, 400, 30);
        formPanel.add(filePathLabel);

        final File[] selectedFile = { null };
        pilihGambarButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Gambar", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                filePathLabel.setText(selectedFile[0].getAbsolutePath());
            }
        });

        // Tombol Donasi
        JButton donasiButtonFinal = new JButton("Donasikan");
        donasiButtonFinal.setBounds(20, 280, 200, 40);
        formPanel.add(donasiButtonFinal);

        donasiButtonFinal.addActionListener(e -> {
            String namaMakanan = namaMakananField.getText();
            String jumlahStr = porsiField.getText();
            String waktuKetersediaan = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTimeSpinner.getValue());
            int jumlah;

            try {
                jumlah = Integer.parseInt(jumlahStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Porsi harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (namaMakanan.isEmpty() || selectedFile[0] == null) {
                JOptionPane.showMessageDialog(frame, "Harap lengkapi semua field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO Makanan (nama, porsi, photo_path, waktu_ketersediaan, id_donatur) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, namaMakanan);
                stmt.setInt(2, jumlah);
                stmt.setString(3, selectedFile[0].getAbsolutePath());
                stmt.setString(4, waktuKetersediaan);
                stmt.setInt(5, userId);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Donasi berhasil disimpan!", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                new BerandaDonatur(userId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Terjadi kesalahan dalam penyimpanan.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Panel Kanan (Animasi/Icon)
        JPanel iconPanel = new JPanel();
        iconPanel.setLayout(new BorderLayout()); // Layout untuk panel kanan
        iconPanel.setPreferredSize(new Dimension(720, 800)); // Set ukuran panel kanan

        // Anda dapat menambahkan animasi atau ikon di sini
        JLabel iconLabel = new JLabel(new ImageIcon("assets\\donasi.png")); // Gantilah dengan path gambar animasi
        iconPanel.add(iconLabel, BorderLayout.CENTER);

        // Menambahkan kedua panel ke panel utama
        donasiPanel.add(formPanel, BorderLayout.WEST); // Panel kiri untuk form input
        donasiPanel.add(iconPanel, BorderLayout.CENTER); // Panel kanan untuk ikon/animasi

        frame.add(donasiPanel);
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
