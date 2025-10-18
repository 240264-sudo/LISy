package com.inventory.UI;

import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.FlatLaf;

/**
 * MerchantLogin — dashboard for merchants
 * Options: Add Store | View Stores
 */
public class MerchantLogin extends javax.swing.JFrame {

    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton addStoreButton;
    private javax.swing.JButton viewStoresButton;

    public MerchantLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        addStoreButton = new javax.swing.JButton();
        viewStoresButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Merchant Login");
        setBounds(new java.awt.Rectangle(500, 100, 0, 0));
        setName("merchantLoginFrame");

        titleLabel.setFont(new java.awt.Font("Poor Richard", 1, 32)); 
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Merchant Dashboard");

        addStoreButton.setText("ADD STORE");
        addStoreButton.setFont(new java.awt.Font("Segoe UI", 0, 18));
        addStoreButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStoreButton.addActionListener(evt -> addStoreButtonActionPerformed(evt));

        viewStoresButton.setText("VIEW STORES");
        viewStoresButton.setFont(new java.awt.Font("Segoe UI", 0, 18));
        viewStoresButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewStoresButton.addActionListener(evt -> viewStoresButtonActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewStoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(addStoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(viewStoresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addStoreButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new AddStore().setVisible(true);
    }

    private void viewStoresButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new ViewStores().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            FlatSolarizedLightIJTheme.setup();
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new MerchantLogin().setVisible(true));
    }
}
