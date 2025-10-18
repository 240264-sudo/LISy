package com.inventory.UI;

import com.inventory.Database.ConnectionFactory;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;

public class CustomerRegistration extends JFrame {

    private JTextField nameText, usernameText, locationText, phoneText;
    private JPasswordField passwordText;
    private JButton registerButton, clearButton, backButton;

    public CustomerRegistration() {
        setTitle("Customer Registration");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Register New Customer", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);
        gbc.gridwidth = 1;

        // Full Name
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        nameText = new JTextField(20);
        panel.add(nameText, gbc);

        // Username
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameText = new JTextField(20);
        panel.add(usernameText, gbc);

        // Password
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordText = new JPasswordField(20);
        panel.add(passwordText, gbc);

        // Location
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        locationText = new JTextField(20);
        panel.add(locationText, gbc);

        // Phone
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneText = new JTextField(20);
        panel.add(phoneText, gbc);

        // Buttons
        gbc.gridy++;
        gbc.gridx = 0;
        registerButton = new JButton("REGISTER");
        panel.add(registerButton, gbc);

        gbc.gridx = 1;
        clearButton = new JButton("CLEAR");
        panel.add(clearButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        backButton = new JButton("BACK");
        panel.add(backButton, gbc);

        add(panel, BorderLayout.CENTER);

        // Button actions
        registerButton.addActionListener(e -> registerCustomer());
        clearButton.addActionListener(e -> clearFields());
        backButton.addActionListener(e -> {
            dispose();
            new CustomerLogin().setVisible(true);
        });

        setVisible(true);
    }

    private void registerCustomer() {
        String fullName = nameText.getText().trim();
        String username = usernameText.getText().trim();
        String password = new String(passwordText.getPassword()).trim();
        String location = locationText.getText().trim();
        String phone = phoneText.getText().trim();

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || location.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try (Connection conn = new ConnectionFactory().getConn()) {
            String query = "INSERT INTO users (name, username, password, location, phone, usertype) VALUES (?, ?, ?, ?, ?, 'CUSTOMER')";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, fullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.setString(4, location);
            pst.setString(5, phone);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Customer registered successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Try again.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        
        new CustomerLogin().setVisible(true);
        dispose();
    }

    private void clearFields() {
        nameText.setText("");
        usernameText.setText("");
        passwordText.setText("");
        locationText.setText("");
        phoneText.setText("");
    }

    public static void main(String[] args) {
        try {
            FlatSolarizedLightIJTheme.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("Component.arc", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(CustomerRegistration::new);
    }
}
