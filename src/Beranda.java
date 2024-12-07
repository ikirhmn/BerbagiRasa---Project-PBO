package src;

import javax.swing.*;
import java.awt.*;

public class Beranda extends JFrame {

    private int userId;

    public Beranda(int userId) {
        this.userId = userId;
        initComponents();
    }

    private void initComponents() {
        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());
    
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(82, 170, 94));
    
        // Navigasi
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        navPanel.setBackground(new Color(82, 170, 94));
    
        JLabel appNameLabel = new JLabel("BerbagiRasa");
        appNameLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        appNameLabel.setForeground(Color.WHITE);
        navPanel.add(appNameLabel);
    
        String[] navItems = {"Beranda", "Donasi", "Riwayat"};
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setFont(new Font("Poppins", Font.PLAIN, 14));
            navButton.setBackground(new Color(82, 170, 94));
            navButton.setForeground(Color.WHITE);
            navButton.setFocusPainted(false);
            navButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            navPanel.add(navButton);
        }
    
        // Tombol profil (pojok kanan)
        JButton profileButton = new JButton();
        profileButton.setPreferredSize(new Dimension(40, 40));
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        profilePanel.setBackground(new Color(82, 170, 94));
        profilePanel.add(profileButton);
    
        headerPanel.add(navPanel, BorderLayout.WEST);
        headerPanel.add(profilePanel, BorderLayout.EAST);
    
        // Membuat JLayeredPane untuk banner
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1440, 300));
    
        // Gambar Banner
        ImageIcon bannerIcon = new ImageIcon("assets/banner beranda.jpg"); // Ganti dengan path gambar banner
        JLabel bannerLabel = new JLabel(new ImageIcon(bannerIcon.getImage().getScaledInstance(1440, 300, Image.SCALE_SMOOTH)));
        bannerLabel.setBounds(0, 0, 900, 300); // Gambar memenuhi seluruh pane
        layeredPane.add(bannerLabel, Integer.valueOf(1)); // Tambahkan di lapisan 1 (bawah)
    
        // Slogan
        JLabel sloganLabel = new JLabel(
                "<html><div style='text-align: center; color: white;'>\"Bagikan surplus makananmu untuk membantu panti asuhan yang membutuhkan.\"</div></html>");
        sloganLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sloganLabel.setBounds(0, 120, 900, 50); // Slogan di tengah gambar
        layeredPane.add(sloganLabel, Integer.valueOf(2)); // Tambahkan di lapisan 2 (atas)
    
        // Tambahkan JLayeredPane ke panel utama
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(layeredPane, BorderLayout.CENTER);
    
        // Konten
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
    
        // Judul daftar makanan
        JLabel daftarMakananLabel = new JLabel("Daftar Makanan");
        daftarMakananLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        daftarMakananLabel.setHorizontalAlignment(SwingConstants.LEFT);
        daftarMakananLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));
        contentPanel.add(daftarMakananLabel, BorderLayout.NORTH);
    
        // Daftar makanan
        JPanel foodPanel = new JPanel(new GridLayout(1, 4, 15, 15));
        foodPanel.setBackground(Color.WHITE);
        foodPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        // Ambil data dari database
        FoodConnection foodService = new FoodConnection();
        for (String[] food : foodService.getFoods(userId)) {
            foodPanel.add(createFoodCard(food[0], food[1], food[2]));
        }
    
        contentPanel.add(foodPanel, BorderLayout.CENTER);
    
        // Tombol tambah donasi
        JButton addDonationButton = new JButton("Tambah Donasi");
        addDonationButton.setFont(new Font("Poppins", Font.BOLD, 14));
        addDonationButton.setBackground(new Color(0, 153, 51));
        addDonationButton.setForeground(Color.WHITE);
        addDonationButton.setFocusPainted(false);
        addDonationButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addDonationButton);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        mainPanel.add(contentPanel, BorderLayout.SOUTH);
    
        // Mengatur frame
        setContentPane(mainPanel);
        setTitle("BerbagiRasa");
        setSize(1440, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    

    private JPanel createFoodCard(String name, String portions, String imagePath) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Gambar makanan
        JLabel imageLabel = new JLabel();
        ImageIcon foodIcon = new ImageIcon(imagePath);
        imageLabel.setIcon(new ImageIcon(foodIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);

        // Info makanan
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 5));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 14));
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(nameLabel);

        JLabel portionsLabel = new JLabel(portions + " Porsi");
        portionsLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        portionsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(portionsLabel);

        card.add(infoPanel, BorderLayout.SOUTH);

        return card;
    }
}
