package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DetailMakanan extends JDialog {
    
    public DetailMakanan(JFrame parent, String imagePath, String namaMakanan, String porsi) {
        super(parent, "Detail Makanan", true);
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Panel Foto Makanan (Kiri)
        JPanel panelKiri = new JPanel();
        panelKiri.setLayout(new BorderLayout());
        panelKiri.setBackground(Color.WHITE);
        
        // Gambar Makanan
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelKiri.add(imageLabel, BorderLayout.CENTER);
        
        // Panel Detail Makanan (Kanan)
        JPanel panelDetail = new JPanel();
        panelDetail.setLayout(new GridLayout(6, 2, 10, 10));
        panelDetail.setBackground(Color.WHITE);
        panelDetail.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding di sekitar panel

        // Nama Makanan
        JLabel lblNamaMakanan = new JLabel("Nama Makanan:");
        JLabel lblNamaMakananValue = new JLabel(namaMakanan);
        panelDetail.add(lblNamaMakanan);
        panelDetail.add(lblNamaMakananValue);

        // Porsi
        JLabel lblPorsi = new JLabel("Jumlah Porsi:");
        JLabel lblPorsiValue = new JLabel(porsi);
        panelDetail.add(lblPorsi);
        panelDetail.add(lblPorsiValue);

        // Waktu Kesediaan
        JLabel lblWaktu = new JLabel("Waktu Kesediaan:");
        JLabel lblWaktuValue = new JLabel("12:00 PM"); // Misalnya waktu kesediaan
        panelDetail.add(lblWaktu);
        panelDetail.add(lblWaktuValue);

        // Status
        JLabel lblStatus = new JLabel("Status:");
        JLabel lblStatusValue = new JLabel("Belum ACC");
        panelDetail.add(lblStatus);
        panelDetail.add(lblStatusValue);

        // Nama Panti
        JLabel lblNamaPanti = new JLabel("Nama Panti:");
        JLabel lblNamaPantiValue = new JLabel("Panti Asuhan ABC");
        panelDetail.add(lblNamaPanti);
        panelDetail.add(lblNamaPantiValue);

        // Alamat Panti
        JLabel lblAlamatPanti = new JLabel("Alamat Panti:");
        JLabel lblAlamatPantiValue = new JLabel("Jl. Panti Asuhan No. 1");
        panelDetail.add(lblAlamatPanti);
        panelDetail.add(lblAlamatPantiValue);

        // Tombol ACC
        JButton btnACC = new JButton("ACC");
        btnACC.setBackground(new Color(82, 170, 94));
        btnACC.setForeground(Color.WHITE);
        btnACC.setFont(new Font("Arial", Font.BOLD, 16));
        btnACC.setFocusPainted(false);
        btnACC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblStatusValue.setText("ACC");
                btnACC.setEnabled(false); // Disable tombol setelah ACC
            }
        });

        // Menambahkan Tombol ACC di bawah panel detail
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(btnACC);

        // Panel utama untuk menampilkan foto dan detail makanan
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(panelKiri, BorderLayout.WEST);
        contentPanel.add(panelDetail, BorderLayout.CENTER);
        
        // Menambahkan contentPanel dan tombol di bawah
        add(contentPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }
}

