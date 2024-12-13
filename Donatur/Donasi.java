package Donatur;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Donasi {
    private int userId; // Menambahkan atribut userId

    public Donasi(int userId) {
        this.userId = userId;
        // Membuat JFrame untuk Donasi
        JFrame frame = new JFrame("Donasi");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(null);

        // Membuat HeaderPanel yang sama dengan Beranda
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
                frame.dispose(); // Menutup JFrame Donasi
            }
        });

        berandaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka Frame Riwayat
                new BerandaDonatur(userId); // Menutup JFrame utama jika diperlukan
                frame.dispose();
            }
        });

        // Button Profile
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Membuka Frame Profile
                new ProfileDonatur(userId);
                frame.dispose(); // Menutup JFrame Donasi
            }
        });

        frame.add(headerPanel);

        // Membuat label "Donasi"
        JLabel donasiLabel = new JLabel("Donasi", JLabel.CENTER);
        donasiLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(donasiLabel, BorderLayout.NORTH);

        // Membuat panel utama untuk menampung kedua panel kiri dan kanan
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2)); // Panel kiri dan kanan sejajar horizontal
        frame.add(mainPanel, BorderLayout.CENTER);

        // Panel kiri untuk form donasi
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Layout vertikal
        mainPanel.add(leftPanel);

        // Label dan input field untuk form donasi
        JLabel namaMakananLabel = new JLabel("Nama Makanan:");
        JTextField namaMakananField = new JTextField(20);

        JLabel jumlahPorsiLabel = new JLabel("Jumlah Porsi:");
        JTextField jumlahPorsiField = new JTextField(20);

        JLabel waktuKesediaanLabel = new JLabel("Waktu Kesediaan:");
        JTextField waktuKesediaanField = new JTextField(20);

        JLabel gambarLabel = new JLabel("Upload Gambar Makanan:");
        JButton uploadButton = new JButton("Pilih Gambar");

        // Menambahkan komponen-komponen ke panel kiri
        leftPanel.add(namaMakananLabel);
        leftPanel.add(namaMakananField);
        leftPanel.add(jumlahPorsiLabel);
        leftPanel.add(jumlahPorsiField);
        leftPanel.add(waktuKesediaanLabel);
        leftPanel.add(waktuKesediaanField);
        leftPanel.add(gambarLabel);
        leftPanel.add(uploadButton);

        // Panel kanan untuk animasi (hanya icon sebagai placeholder)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout()); // Layout untuk menempatkan animasi
        mainPanel.add(rightPanel);

        // Menambahkan label untuk animasi (ganti dengan animasi atau gambar yang
        // sesuai)
        JLabel animationLabel = new JLabel("Animasi Gambar Makanan", JLabel.CENTER);
        animationLabel.setIcon(new ImageIcon("assets\\donasi.png")); // Ganti dengan path ke gambar animasi
        rightPanel.add(animationLabel, BorderLayout.CENTER);

        // Membuat frame terlihat
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

    // Fungsi untuk menampilkan frame Riwayat
    public static void main(String[] args) {
        new Donasi(1);
    }
}
