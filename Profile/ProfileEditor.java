
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.DatabaseConnection;

public class ProfileEditor extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField contactNumberField;
    private JComboBox<String> cityComboBox;
    private JComboBox<String> stateComboBox;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JButton cancelButton;
    private int userId; // User ID to identify which profile to update

    public ProfileEditor(int userId) {
        this.userId = userId;
        setTitle("Edit Profile");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Load user data for editing
        loadUserData();

        setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 5, 10));
        panel.setBackground(new Color(75, 163, 88));

        // Placeholder icons (replace paths with actual image paths if available)
        Icon homeIcon = new ImageIcon("Profile//home.png");
        Icon notificationIcon = new ImageIcon("Profile//notification.png");
        Icon securityIcon = new ImageIcon("Profile//Keamanan.png");
        Icon appearanceIcon = new ImageIcon("appearance.png");
        Icon helpIcon = new ImageIcon("Profile//help.png");
        Icon settingsIcon = new ImageIcon("Profile//settings.png");

        // Resizing icons
        int iconWidth = 40;
        int iconHeight = 40;
        homeIcon = resizeIcon(homeIcon, iconWidth, iconHeight);
        notificationIcon = resizeIcon(notificationIcon, iconWidth, iconHeight);
        securityIcon = resizeIcon(securityIcon, iconWidth, iconHeight);
        appearanceIcon = resizeIcon(appearanceIcon, iconWidth, iconHeight);
        helpIcon = resizeIcon(helpIcon, iconWidth, iconHeight);
        settingsIcon = resizeIcon(settingsIcon, iconWidth, iconHeight);

        // Adding icons with labels
        panel.add(createLabelWithIcon("Home", homeIcon));
        panel.add(createLabelWithIcon("Notification", notificationIcon));
        panel.add(createLabelWithIcon("Security", securityIcon));
        panel.add(createLabelWithIcon("Appearance", appearanceIcon));
        panel.add(createLabelWithIcon("Help", helpIcon));
        panel.add(createLabelWithIcon("Settings", settingsIcon));

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 5, 10));

        panel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("Contact Number:"));
        contactNumberField = new JTextField();
        panel.add(contactNumberField);

        panel.add(new JLabel("City:"));
        cityComboBox = new JComboBox<>(new String[]{"Select City", "Lombok Timur", "Mataram", "Lombok Tengah", "KLU"});
        panel.add(cityComboBox);

        panel.add(new JLabel("State:"));
        stateComboBox = new JComboBox<>(new String[]{"Select State", "State1", "State2"});
        panel.add(stateComboBox);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProfile();
            }
        });
        panel.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cancelButton);

        return panel;
    }

    private void loadUserData() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT first_name, last_name, email, address, contact_number, city, state FROM Users WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    firstNameField.setText(rs.getString("first_name"));
                    lastNameField.setText(rs.getString("last_name"));
                    emailField.setText(rs.getString("email"));
                    addressField.setText(rs.getString("address"));
                    contactNumberField.setText(rs.getString("contact_number"));
                    cityComboBox.setSelectedItem(rs.getString("city"));
                    stateComboBox.setSelectedItem(rs.getString("state"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading user data.");
        }
    }

    private void updateProfile() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String updateQuery = "UPDATE Users SET first_name = ?, last_name = ?, email = ?, address = ?, contact_number = ?, city = ?, state = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateQuery)) {
                stmt.setString(1, firstNameField.getText());
                stmt.setString(2, lastNameField.getText());
                stmt.setString(3, emailField.getText());
                stmt.setString(4, addressField.getText());
                stmt.setString(5, contactNumberField.getText());
                stmt.setString(6, (String) cityComboBox.getSelectedItem());
                stmt.setString(7, (String) stateComboBox.getSelectedItem());
                stmt.setInt(8, userId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Profile updated successfully!");
                dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating profile.");
        }
    }

    private Icon resizeIcon(Icon icon, int width, int height) {
        if (icon instanceof ImageIcon) {
            Image img = ((ImageIcon) icon).getImage();
            return new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH));
        }
        return icon;
    }

    private JLabel createLabelWithIcon(String text, Icon icon) {
        JLabel label = new JLabel(text, icon, JLabel.LEFT);
        label.setIconTextGap(5);
        return label;
    }

    public static void main(String[] args) {
        new ProfileEditor(1); // Pass user ID as a parameter
    }
}
