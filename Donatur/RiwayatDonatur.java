package Donatur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        headerPanel.setBackground(new Color(50, 150, 64));
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

        JButton donasiButton = createTransparentButton("Donasi");
        donasiButton.setBounds(440, 20, 100, 40);
        donasiButton.setFont(new Font("Poppins", Font.PLAIN, 16));
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

        // Menambahkan HeaderPanel ke Frame
        rootPanel.add(headerPanel, BorderLayout.NORTH);

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

        // Panel Utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Gunakan BoxLayout untuk panel yang
                                                                         // scrollable
        mainPanel.setBackground(new Color(245, 245, 245));

        // Label Riwayat
        JLabel riwayatLabel = new JLabel("Riwayat");
        riwayatLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        riwayatLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Pastikan label rata kiri
        mainPanel.add(riwayatLabel);

        // Dummy Data
        ArrayList<String[]> dataList = new ArrayList<>();
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Bakso Ayam", "12 Desember 2024", "Panti Asuhan A", "5 Porsi" });
        dataList.add(new String[] { "assets\\bakso.jpg", "Nasi Goreng", "14 Desember 2024", "Panti Asuhan B",
                "10 Porsi" });
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Mie Ayam", "15 Desember 2024", "Panti Asuhan C", "8 Porsi" });
        dataList.add(new String[] { "assets\\bakso.jpg", "Nasi Goreng", "14 Desember 2024", "Panti Asuhan B",
                "10 Porsi" });
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Mie Ayam", "15 Desember 2024", "Panti Asuhan C", "8 Porsi" });
        dataList.add(new String[] { "assets\\bakso.jpg", "Nasi Goreng", "14 Desember 2024", "Panti Asuhan B",
                "10 Porsi" });
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Mie Ayam", "15 Desember 2024", "Panti Asuhan C", "8 Porsi" });
        dataList.add(new String[] { "assets\\bakso.jpg", "Nasi Goreng", "14 Desember 2024", "Panti Asuhan B",
                "10 Porsi" });
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Mie Ayam", "15 Desember 2024", "Panti Asuhan C", "8 Porsi" });
        dataList.add(new String[] { "assets\\bakso.jpg", "Nasi Goreng", "14 Desember 2024", "Panti Asuhan B",
                "10 Porsi" });
        dataList.add(
                new String[] { "assets\\bakso.jpg", "Mie Ayam", "15 Desember 2024", "Panti Asuhan C", "8 Porsi" });

        // Membuat card
        for (String[] data : dataList) {
            JPanel card = createCard(data[0], data[1], data[2], data[3], data[4]);
            card.setAlignmentX(Component.LEFT_ALIGNMENT); // Pastikan setiap card rata kiri
            mainPanel.add(card);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak antar kartu
        }

        // Membungkus mainPanel dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Agar scroll vertikal selalu
                                                                                      // muncul

        // Menambahkan scrollPane ke rootPanel
        rootPanel.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan rootPanel ke frame
        frame.add(rootPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    // Fungsi untuk membuat card
    private static JPanel createCard(String imagePath, String namaMakanan, String tanggal, String namaPanti,
            String jumlahPorsi) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(10, 10));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setPreferredSize(new Dimension(800, 180)); // Ukuran kartu tetap proporsional

        // Gambar
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(
                new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        // Informasi
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel namaLabel = new JLabel("<html><b>" + namaMakanan + "</b></html>");
        namaLabel.setFont(new Font("Poppins", Font.BOLD, 24));

        JLabel tanggalLabel = new JLabel("Tanggal: " + tanggal);
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

        // Tambahkan ke card
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

    // Fungsi untuk menampilkan frame Riwayat
    public static void main(String[] args) {
        new RiwayatDonatur(123);
    }
}
