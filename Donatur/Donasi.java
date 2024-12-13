package Donatur;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.DatabaseConnection;

public class Donasi {
    private int userId;

    public Donasi(int userId) {
        this.userId = userId;

        // Membuat JFrame untuk Donasi
        JFrame frame = new JFrame("Donasi");
        frame.setSize(1440, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1440, 80);
        headerPanel.setBackground(new Color(82, 170, 94));
        headerPanel.setLayout(null);

        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);

        frame.add(headerPanel);

        // Panel Konten Donasi
        JPanel donasiPanel = new JPanel();
        donasiPanel.setBounds(0, 80, 1440, 800);
        donasiPanel.setLayout(null);

        JLabel donasiLabel = new JLabel("<html><b>Form Donasi</b></html>");
        donasiLabel.setBounds(20, 20, 400, 40);
        donasiLabel.setFont(new Font("Arial", Font.BOLD, 24));
        donasiPanel.add(donasiLabel);

        // Input Nama Makanan
        JLabel namaMakananLabel = new JLabel("Nama Makanan:");
        namaMakananLabel.setBounds(20, 80, 200, 30);
        namaMakananLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        donasiPanel.add(namaMakananLabel);

        JTextField namaMakananField = new JTextField();
        namaMakananField.setBounds(220, 80, 400, 30);
        donasiPanel.add(namaMakananField);

        // Input Porsi
        JLabel jumlahLabel = new JLabel("Porsi:");
        jumlahLabel.setBounds(20, 130, 200, 30);
        jumlahLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        donasiPanel.add(jumlahLabel);

        JTextField porsiField = new JTextField();
        porsiField.setBounds(220, 130, 400, 30);
        donasiPanel.add(porsiField);

        // Input Waktu Ketersediaan
        JLabel ketersediaanLabel = new JLabel("Waktu Ketersediaan:");
        ketersediaanLabel.setBounds(20, 180, 200, 30);
        ketersediaanLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        donasiPanel.add(ketersediaanLabel);

        JSpinner dateTimeSpinner = new JSpinner(new SpinnerDateModel());
        dateTimeSpinner.setBounds(220, 180, 400, 30);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateTimeSpinner, "yyyy-MM-dd HH:mm:ss");
        dateTimeSpinner.setEditor(dateEditor);
        donasiPanel.add(dateTimeSpinner);

        // Input Gambar
        JLabel gambarLabel = new JLabel("Gambar Makanan:");
        gambarLabel.setBounds(20, 230, 200, 30);
        gambarLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        donasiPanel.add(gambarLabel);

        JButton pilihGambarButton = new JButton("Pilih Gambar");
        pilihGambarButton.setBounds(220, 230, 200, 30);
        donasiPanel.add(pilihGambarButton);

        JLabel filePathLabel = new JLabel("Belum ada file yang dipilih");
        filePathLabel.setBounds(430, 230, 400, 30);
        donasiPanel.add(filePathLabel);

        final File[] selectedFile = {null};
        pilihGambarButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Gambar", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(frame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile[0] = fileChooser.getSelectedFile();
                filePathLabel.setText(selectedFile[0].getAbsolutePath());
            }
        });

        // Tombol Donasi
        JButton donasiButtonFinal = new JButton("Donasikan");
        donasiButtonFinal.setBounds(20, 280, 200, 40);
        donasiPanel.add(donasiButtonFinal);

        donasiButtonFinal.addActionListener(e -> {
            String namaMakanan = namaMakananField.getText();
            String jumlahStr = porsiField.getText();
            String waktuKetersediaan = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTimeSpinner.getValue());
            int jumlah;

            try {
                jumlah = Integer.parseInt(jumlahStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Porsi harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (namaMakanan.isEmpty() || selectedFile[0] == null) {
                JOptionPane.showMessageDialog(frame, "Harap lengkapi semua field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection conn = DatabaseConnection.getConnection()) {
                String query = "INSERT INTO Makanan (nama, porsi, photo_path, waktu_ketersediaan, id_donatur) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, namaMakanan);
                stmt.setInt(2, jumlah);
                stmt.setString(3, selectedFile[0].getAbsolutePath());
                stmt.setString(4, waktuKetersediaan);
                stmt.setInt(5, userId);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Donasi berhasil disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                new BerandaDonatur(userId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Terjadi kesalahan dalam penyimpanan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.add(donasiPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
}
