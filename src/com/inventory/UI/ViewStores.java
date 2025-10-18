package com.inventory.UI;

import com.inventory.Database.ConnectionFactory;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.FlatLaf;

/**
 * ViewStores — displays all stores from the 'stores' table
 * Allows merchants to view and delete store records
 */
public class ViewStores extends javax.swing.JFrame {

    private javax.swing.JLabel titleLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable storeTable;
    private javax.swing.JButton backButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton refreshButton;

    private DefaultTableModel tableModel;

    public ViewStores() {
        initComponents();
        loadStores();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        storeTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Stores");
        setBounds(new java.awt.Rectangle(300, 100, 0, 0));
        setName("viewStoresFrame");

        titleLabel.setFont(new java.awt.Font("Poor Richard", 1, 28));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Registered Stores");

        storeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Store ID", "Store Name", "Owner ID", "Location", "X Coordinate", "Y Coordinate"}
        ));
        jScrollPane1.setViewportView(storeTable);

        backButton.setText("BACK");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(evt -> backButtonActionPerformed(evt));

        deleteButton.setText("DELETE STORE");
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.addActionListener(evt -> deleteButtonActionPerformed(evt));

        refreshButton.setText("REFRESH");
        refreshButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refreshButton.addActionListener(evt -> loadStores());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(30)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30))
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Load all stores into table
    private void loadStores() {
        tableModel = (DefaultTableModel) storeTable.getModel();
        tableModel.setRowCount(0);

        String query = "SELECT * FROM stores ORDER BY store_id";

        try (Connection conn = new ConnectionFactory().getConn();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("store_id");
                String name = rs.getString("store_name");
                String owner = rs.getString("owner_id");
                String location = rs.getString("location");
                int x = rs.getInt("x_coordinate");
                int y = rs.getInt("y_coordinate");

                tableModel.addRow(new Object[]{id, name, owner, location, x, y});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading stores: " + e.getMessage());
        }
    }

    // Delete selected store
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = storeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a store to delete.");
            return;
        }

        int storeId = (int) storeTable.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete store ID " + storeId + "?", 
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = new ConnectionFactory().getConn()) {
                String query = "DELETE FROM stores WHERE store_id = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, storeId);
                int rows = pst.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Store deleted successfully!");
                    loadStores();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete store.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting store: " + e.getMessage());
            }
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new MerchantLogin().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            FlatSolarizedLightIJTheme.setup();
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new ViewStores().setVisible(true));
    }
}
