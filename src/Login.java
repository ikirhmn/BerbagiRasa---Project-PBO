package src;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    public Login() {
        // Set title dan ukuran awal jendela
        setTitle("Login - BerbagiRasa");
        setSize(900, 600); // Ubah ukuran agar lebih proporsional
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Posisi di tengah layar
        setResizable(false);

        // Gunakan BorderLayout untuk membagi panel kiri dan kanan
        setLayout(new BorderLayout());

        // Panel kiri (Logo dan slogan)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(82, 170, 90));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Layout vertikal
        leftPanel.setPreferredSize(new Dimension(400, 600));

        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/assets/logo1.png"));
        logoLabel.setIcon(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("BerbagiRasa");
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel sloganLabel = new JLabel("“Wujudkan Cinta dengan Makanan Berlebih”");
        sloganLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        sloganLabel.setForeground(Color.WHITE);
        sloganLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Tambahkan komponen ke panel kiri dengan spasi
        leftPanel.add(Box.createVerticalStrut(150)); // Spasi atas
        leftPanel.add(logoLabel);
        leftPanel.add(Box.createVerticalStrut(20)); // Spasi antar komponen
        leftPanel.add(titleLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(sloganLabel);

        // Panel kanan (Form login)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setLayout(new GridBagLayout()); // Layout GridBag untuk posisi fleksibel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Poppins", Font.BOLD, 36));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 30));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 30));

        JButton loginButton = new JButton("Masuk");
        loginButton.setFont(new Font("Poppins", Font.BOLD, 14));
        loginButton.setBackground(new Color(55, 196, 199));
        loginButton.setForeground(Color.WHITE);

        // Tambahkan elemen ke panel kanan dengan GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Login title di tengah
        rightPanel.add(loginTitle, gbc);

        gbc.gridwidth = 1; // Reset gridwidth
        gbc.gridx = 0;
        gbc.gridy = 1;
        rightPanel.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        rightPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        rightPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        rightPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Tombol login di tengah
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(loginButton, gbc);

        // Tambahkan panel kiri dan kanan ke JFrame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        });
    }
}
