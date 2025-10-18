package com.inventory.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class CustomerProductSearch extends JFrame {

    private JTextField itemField;
    private JTextField locationField;
    private JButton searchButton, backButton;

    public CustomerProductSearch() {
        setTitle("Customer Product Search");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Search for Products", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Item Name:"), gbc);

        gbc.gridx = 1;
        itemField = new JTextField(15);
        add(itemField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Location:"), gbc);

        gbc.gridx = 1;
        locationField = new JTextField(15);
        add(locationField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        searchButton = new JButton("SEARCH");
        add(searchButton, gbc);

        gbc.gridx = 1;
        backButton = new JButton("BACK");
        add(backButton, gbc);

        // Button actions
        searchButton.addActionListener(this::onSearchClicked);
        backButton.addActionListener(e -> {
            dispose();
            new CustomerLogin().setVisible(true);
        });

        setVisible(true);
    }

    private void onSearchClicked(ActionEvent e) {
        String item = itemField.getText().trim();
        String location = locationField.getText().trim();

        if (item.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a product name to search.");
            return;
        }

        // Pass the values to SearchResult
        dispose();
        new SearchResult(item, location);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomerProductSearch::new);
    }
}
