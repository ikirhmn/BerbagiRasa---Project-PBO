package src;

import javax.swing.*;
import java.awt.*;

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

        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);

        // Tombol di header
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

        frame.setLayout(null);
        frame.add(headerPanel);

        // Panel pertama di sebelah kiri untuk gambar animasi
        JPanel panelPertama = new JPanel();
        panelPertama.setBounds(50, 100, 650, 700);
        panelPertama.setBackground(new Color(240, 240, 240));
        panelPertama.setLayout(new BorderLayout());

        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("assets\\loginas.png"); // Ganti path gambar sesuai kebutuhan
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
        donaturButton.setBackground(new Color(82, 170, 94)); // Warna sesuai RGB
        donaturButton.setForeground(Color.WHITE); // Warna teks putih
        panelKedua.add(donaturButton);

        // Memberikan jarak lebih rapat antar tombol
        panelKedua.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak antar tombol lebih rapat

        // Tombol "Login as Panti Asuhan"
        JButton pantiButton = new JButton("Login as Panti Asuhan");
        pantiButton.setFont(new Font("Arial", Font.PLAIN, 18));
        pantiButton.setPreferredSize(new Dimension(250, 40)); // Ukuran tombol
        pantiButton.setBackground(new Color(82, 170, 94)); // Warna sesuai RGB
        pantiButton.setForeground(Color.WHITE); // Warna teks putih
        panelKedua.add(pantiButton);

        // Menambahkan kedua panel ke dalam frame
        frame.add(panelPertama);
        frame.add(panelKedua);

        // Menampilkan JFrame utama
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
