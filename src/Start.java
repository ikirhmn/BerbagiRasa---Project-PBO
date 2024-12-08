package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Start {
    public static void main(String[] args) {

        JFrame frame = new JFrame("BerbagiRasa");
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

        frame.add(headerPanel);

        // Panel Pertama (Sebelah Kiri)
        JPanel panelPertama = new JPanel();
        panelPertama.setBounds(50, 150, 650, 700);
        panelPertama.setBackground(new Color(240, 240, 240));
        panelPertama.setLayout(null);

        // Judul
        JLabel titleLabel = new JLabel("BerbagiRasa");
        titleLabel.setBounds(30, 50, 600, 50);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 54));
        titleLabel.setForeground(new Color(82, 170, 94));
        panelPertama.add(titleLabel);

        // Slogan
        JLabel sloganLabel = new JLabel("<html><div style='text-align: justify;'>"
                + "Selamat datang di platform yang tepat untuk menghubungkan<br>"
                + "kebaikan Anda dengan mereka yang membutuhkan.<br>"
                + "Kami hadir untuk membantu mendistribusikan surplus makanan<br>"
                + "Anda kepada panti asuhan, menciptakan kebahagiaan dan kebermanfaatan<br>"
                + "di tengah kehidupan yang penuh makna."
                + "</div></html>");
        sloganLabel.setBounds(30, 120, 600, 200); // Tinggi label cukup untuk menampung teks
        sloganLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        sloganLabel.setForeground(Color.DARK_GRAY);
        sloganLabel.setVerticalAlignment(SwingConstants.TOP);
        panelPertama.add(sloganLabel);



        // Tombol "Mulai Jelajahi"
        JButton mulaiButton = new JButton("Mulai Jelajahi");
        mulaiButton.setBounds(30, 300, 200, 50);
        mulaiButton.setBackground(new Color(82, 170, 94));
        mulaiButton.setForeground(Color.WHITE);
        mulaiButton.setFont(new Font("Poppins", Font.BOLD, 18));
        mulaiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mulaiButton.setFocusPainted(false);
        mulaiButton.setBorderPainted(false);
        panelPertama.add(mulaiButton);

          // ActionListener untuk membuka halaman Login
          ActionListener goToLogin = e -> { 
            Login loginFrame = new Login(); // Membuat instance dari halaman login
            loginFrame.setVisible(true); // Menampilkan halaman login
          };

          // Tambahkan ActionListener ke semua tombol
          berandaButton.addActionListener(goToLogin);
          donasiButton.addActionListener(goToLogin);
          riwayatButton.addActionListener(goToLogin);
          profileButton.addActionListener(goToLogin);

          ActionListener mulai = e -> {
            // Membuka kelas loginas
            new loginas();
        };
        
        // Menambahkan ActionListener ke tombol "Mulai Jelajahi"
        mulaiButton.addActionListener(mulai);
  

        frame.add(panelPertama);

        // Panel Kedua (Sebelah Kanan)
        JPanel panelKedua = new JPanel();
        panelKedua.setBounds(750, 70, 600, 700);
        panelKedua.setBackground(new Color(240, 240, 240));
        panelKedua.setLayout(new BorderLayout());

        // Gambar atau Animasi (Gantilah path dengan lokasi file gambar Anda)
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("assets\\start.png");
        imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        panelKedua.add(imageLabel, BorderLayout.CENTER);

        frame.add(panelKedua);

        // Tampilkan Frame
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
