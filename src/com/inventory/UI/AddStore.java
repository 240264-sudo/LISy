package com.inventory.UI;

import com.inventory.Database.ConnectionFactory;
import java.sql.*;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.FlatLaf;

/**
 * AddStore — lets a merchant register a new store
 * Inserts into the 'stores' table
 */
public class AddStore extends javax.swing.JFrame {

    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JLabel xLabel;
    private javax.swing.JLabel yLabel;
    private javax.swing.JTextField storeNameField;
    private javax.swing.JTextField locationField;
    private javax.swing.JTextField ownerIdField;
    private javax.swing.JTextField xCoordField;
    private javax.swing.JTextField yCoordField;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;

    public AddStore() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        ownerLabel = new javax.swing.JLabel();
        xLabel = new javax.swing.JLabel();
        yLabel = new javax.swing.JLabel();

        storeNameField = new javax.swing.JTextField();
        locationField = new javax.swing.JTextField();
        ownerIdField = new javax.swing.JTextField();
        xCoordField = new javax.swing.JTextField();
        yCoordField = new javax.swing.JTextField();

        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Store");
        setBounds(new java.awt.Rectangle(500, 100, 0, 0));
        setName("addStoreFrame");

        titleLabel.setFont(new java.awt.Font("Poor Richard", 1, 28)); 
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Add New Store");

        nameLabel.setText("Store Name:");
        locationLabel.setText("Location:");
        ownerLabel.setText("Owner ID:");
        xLabel.setText("X Coordinate:");
        yLabel.setText("Y Coordinate:");

        addButton.setText("ADD STORE");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addActionListener(evt -> addButtonActionPerformed(evt));

        backButton.setText("BACK");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(evt -> backButtonActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameLabel)
                            .addComponent(locationLabel)
                            .addComponent(ownerLabel)
                            .addComponent(xLabel)
                            .addComponent(yLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(storeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(locationField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ownerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(xCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(40)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ownerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ownerIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(xLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yCoordField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String storeName = storeNameField.getText().trim();
        String location = locationField.getText().trim();
        String ownerId = ownerIdField.getText().trim();
        String xText = xCoordField.getText().trim();
        String yText = yCoordField.getText().trim();

        if (storeName.isEmpty() || location.isEmpty() || ownerId.isEmpty() || xText.isEmpty() || yText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try (Connection conn = new ConnectionFactory().getConn()) {
            String query = "INSERT INTO stores (store_name, owner_id, location, x_coordinate, y_coordinate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, storeName);
            pst.setString(2, ownerId);
            pst.setString(3, location);
            pst.setInt(4, Integer.parseInt(xText));
            pst.setInt(5, Integer.parseInt(yText));

            int rows = pst.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Store added successfully!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add store.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new MerchantLogin().setVisible(true);
    }

    private void clearFields() {
        storeNameField.setText("");
        locationField.setText("");
        ownerIdField.setText("");
        xCoordField.setText("");
        yCoordField.setText("");
    }

    public static void main(String[] args) {
        try {
            FlatSolarizedLightIJTheme.setup();
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new AddStore().setVisible(true));
    }
}
