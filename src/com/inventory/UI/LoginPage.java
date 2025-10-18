package com.inventory.UI;

import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.FlatLaf;

/**
 * Simplified LoginPage — just two buttons: Customer and Merchant
 */
public class LoginPage extends javax.swing.JFrame {

    public LoginPage() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        customerButton = new javax.swing.JButton();
        merchantButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setBounds(new java.awt.Rectangle(500, 100, 0, 0));
        setName("loginFrame");

        jLabelTitle.setFont(new java.awt.Font("Poor Richard", 1, 36)); 
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Lisy");

        customerButton.setText("CUSTOMER");
        customerButton.setFont(new java.awt.Font("Segoe UI", 0, 18));
        customerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        customerButton.addActionListener(evt -> customerButtonActionPerformed(evt));

        merchantButton.setText("MERCHANT");
        merchantButton.setFont(new java.awt.Font("Segoe UI", 0, 18));
        merchantButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        merchantButton.addActionListener(evt -> merchantButtonActionPerformed(evt));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(merchantButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(customerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(merchantButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void customerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new CustomerLogin().setVisible(true);
    }

    private void merchantButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new MerchantLogin().setVisible(true); // You can reuse your previous login logic here
    }

    public static void main(String[] args) {
        try {
            FlatSolarizedLightIJTheme.setup();
            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 15);
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton customerButton;
    private javax.swing.JButton merchantButton;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration
}
