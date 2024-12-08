package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Donasi {
    private int userId;  // Menambahkan atribut userId
    public Donasi(int userId) {
        this.userId = userId;
        // Membuat JFrame untuk Donasi
        JFrame frame = new JFrame("Donasi");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

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

        // Panel Konten Donasi
        JPanel donasiPanel = new JPanel();
        donasiPanel.setBounds(0, 80, 1440, 800);
        donasiPanel.setLayout(null);

        // Contoh Konten Donasi
        JLabel donasiLabel = new JLabel("<html><b>Form Donasi</b></html>");
        donasiLabel.setBounds(20, 20, 400, 40);
        donasiLabel.setFont(new Font("Arial", Font.BOLD, 24));
        donasiPanel.add(donasiLabel);

        // Menampilkan form donasi (contoh)
        JLabel namaMakananLabel = new JLabel("Nama Makanan:");
        namaMakananLabel.setBounds(20, 80, 400, 40);
        namaMakananLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        donasiPanel.add(namaMakananLabel);

        JTextField namaMakananField = new JTextField();
        namaMakananField.setBounds(20, 120, 400, 30);
        donasiPanel.add(namaMakananField);

        // Tombol Donasi
        JButton donasiButtonFinal = new JButton("Donasi");
        donasiButtonFinal.setBounds(20, 160, 200, 40);
        donasiPanel.add(donasiButtonFinal);

        // Menambahkan panel ke frame
        frame.add(donasiPanel);

        // Menampilkan JFrame Donasi
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
