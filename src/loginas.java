package src;

import javax.swing.*;

import Donatur.LoginDonatur;
import Panti.LoginPanti;

import java.awt.*;
import java.awt.event.ActionListener;

public class loginas {
    public loginas() {
        // Membuat frame
        JFrame frame = new JFrame("BerbagiRasa");

        // Mengatur ukuran frame
        frame.setSize(1440, 900);

        // Mengatur posisi agar berada di tengah layar
        frame.setLocationRelativeTo(null);

        // Menentukan operasi close pada frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat HeaderPanel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1440, 80);
        headerPanel.setBackground(new Color(82, 170, 94));
        headerPanel.setLayout(null);

        // Menambahkan tombol dengan ikon arrow
        JButton arrowButton = new JButton();
        arrowButton.setBounds(20, 20, 40, 40); // Ukuran dan posisi tombol
        arrowButton.setIcon(new ImageIcon(new ImageIcon("assets\\icon\\left_arrow.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH))); // Path ikon arrow
        arrowButton.setBorderPainted(false); // Hilangkan border
        arrowButton.setFocusPainted(false); // Hilangkan efek fokus
        arrowButton.setContentAreaFilled(false); // Hilangkan background

        // ActionListener untuk membuka halaman Start
        arrowButton.addActionListener(e -> {
            new Start().setVisible(true); // Membuka halaman Start
            SwingUtilities.getWindowAncestor(arrowButton).dispose(); // Menutup frame saat ini
        });

        // Tambahkan tombol ke headerPanel
        headerPanel.add(arrowButton);

        frame.setLayout(null);
        frame.add(headerPanel);

        // Panel pertama di sebelah kiri untuk gambar animasi
        JPanel panelPertama = new JPanel();
        panelPertama.setBounds(50, 100, 650, 700);
        panelPertama.setBackground(new Color(240, 240, 240));
        panelPertama.setLayout(new BorderLayout());

        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("assets\\loginas.png"); // Ganti path gambar sesuai
                                                                                          // kebutuhan
        iconLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH)));
        panelPertama.add(iconLabel, BorderLayout.CENTER);

        // Panel kedua di sebelah kanan untuk tombol login
        JPanel panelKedua = new JPanel();
        panelKedua.setBounds(850, 300, 650, 700);
        panelKedua.setBackground(new Color(240, 240, 240));
        panelKedua.setLayout(new BoxLayout(panelKedua, BoxLayout.Y_AXIS)); // BoxLayout vertikal

        // Mengatur jarak antar tombol lebih rapat
        panelKedua.add(Box.createRigidArea(new Dimension(0, 100))); // Menambah jarak atas

        // Tombol "Login as Donatur"
        JButton donaturButton = new JButton("Login as Donatur");
        donaturButton.setFont(new Font("Arial", Font.PLAIN, 18));
        donaturButton.setPreferredSize(new Dimension(250, 40)); // Ukuran tombol
        donaturButton.setBackground(new Color(19, 85, 137)); // Warna sesuai RGB
        donaturButton.setForeground(Color.WHITE); // Warna teks putih
        donaturButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                donaturButton.setBackground(new Color(37, 139, 220)); // Warna saat hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                donaturButton.setBackground(new Color(19, 85, 137)); // Warna kembali ke semula
            }
        });
        panelKedua.add(donaturButton);

        // Memberikan jarak lebih rapat antar tombol
        panelKedua.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak antar tombol lebih rapat

        // Tombol "Login as Panti Asuhan"
        JButton pantiButton = new JButton("Login as Panti Asuhan");
        pantiButton.setFont(new Font("Arial", Font.PLAIN, 18));
        pantiButton.setPreferredSize(new Dimension(250, 40)); // Ukuran tombol
        pantiButton.setBackground(new Color(19, 85, 137)); // Warna sesuai RGB
        pantiButton.setForeground(Color.WHITE); // Warna teks putih
        pantiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pantiButton.setBackground(new Color(37, 139, 220)); // Warna saat hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pantiButton.setBackground(new Color(19, 85, 137)); // Warna kembali ke semula
            }
        });
        panelKedua.add(pantiButton);

        // ActionListener untuk membuka halaman Login
        ActionListener goToLogin = e -> {
            LoginDonatur loginFrame = new LoginDonatur(); // Membuat instance dari halaman login
            loginFrame.setVisible(true); // Menampilkan halaman login
        };

        donaturButton.addActionListener(goToLogin);

        ActionListener pantiLogin = e -> {
            LoginPanti loginFrame = new LoginPanti(); // Membuat instance dari halaman login
            loginFrame.setVisible(true); // Menampilkan halaman login
        };

        pantiButton.addActionListener(pantiLogin);

        // Menambahkan kedua panel ke dalam frame
        frame.add(panelPertama);
        frame.add(panelKedua);

        // Menampilkan JFrame utama
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

}
