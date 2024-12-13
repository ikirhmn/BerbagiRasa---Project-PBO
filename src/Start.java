package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Start extends JFrame {
    public static void main(String[] args) {

        JFrame frame = new JFrame("BerbagiRasa");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Membuat HeaderPanel yang sama dengan Beranda
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1600, 80);
        headerPanel.setBackground(new Color(50, 150, 64));
        headerPanel.setLayout(null);

        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);


        frame.add(headerPanel);

        // Panel Pertama (Sebelah Kiri)
        JPanel panelPertama = new JPanel();
        panelPertama.setBounds(50, 150, 650, 700);
        panelPertama.setBackground(new Color(240, 240, 240));
        panelPertama.setLayout(null);

        // Judul
        JLabel titleLabel = new JLabel("BerbagiRasa");
        titleLabel.setBounds(30, 50, 600, 80);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 54));
        titleLabel.setForeground(new Color(22, 22, 22));
        panelPertama.add(titleLabel);

        // Slogan
        JLabel sloganLabel = new JLabel("<html><div style='text-align: justify;'>"
                + "Selamat datang di platform yang tepat untuk menghubungkan<br>"
                + "kebaikan Anda dengan mereka yang membutuhkan.<br>"
                + "Kami hadir untuk membantu mendistribusikan surplus makanan<br>"
                + "Anda kepada panti asuhan, menciptakan kebahagiaan dan kebermanfaatan<br>"
                + "di tengah kehidupan yang penuh makna."
                + "</div></html>");
        sloganLabel.setBounds(30, 140, 600, 200); // Tinggi label cukup untuk menampung teks
        sloganLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        sloganLabel.setForeground(Color.DARK_GRAY);
        sloganLabel.setVerticalAlignment(SwingConstants.TOP);
        panelPertama.add(sloganLabel);

        // Tombol "Mulai Jelajahi"
        JButton mulaiButton = new JButton("Mulai Jelajahi");
        mulaiButton.setBounds(30, 300, 200, 50);
        mulaiButton.setBackground(new Color(50, 150, 64));
        mulaiButton.setForeground(Color.WHITE);
        mulaiButton.setFont(new Font("Poppins", Font.BOLD, 18));
        mulaiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mulaiButton.setFocusPainted(false);
        mulaiButton.setBorderPainted(false);
        
        mulaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mulaiButton.setBackground(new Color(70, 216, 89)); // Warna saat hover
            }
        
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mulaiButton.setBackground(new Color(50, 150, 64)); // Warna kembali ke semula
            }
        });

        panelPertama.add(mulaiButton);



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
