/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.UI;

import com.inventory.Database.ConnectionFactory;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.FlatLaf;

/**
 *
 */
public class CustomerLogin extends javax.swing.JFrame {

    LocalDateTime inTime;

    /**
     * Creates new form CustomerLogin
     */
    public CustomerLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        passwordText = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        registerButton = new JButton("REGISTER");
        registerButton.addActionListener(e -> {
            dispose();
            new CustomerRegistration().setVisible(true);
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Login");
        setBounds(new java.awt.Rectangle(500, 100, 0, 0));
        setName("customerLoginFrame");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Username:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Password:");

        jLabel3.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Customer Portal");

        loginButton.setText("LOGIN");
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        backButton.setText("BACK");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGap(47, 47, 47)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameText))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordText))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(52, Short.MAX_VALUE))
);

layout.setVerticalGroup(
    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addGap(44, 44, 44)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(57, 57, 57)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addComponent(usernameText, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)))
        .addGap(40, 40, 40)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(40, 40, 40))
);
        pack();
    }

    // Password Encryption
    private String encryptPass(String pass) {
        String encPass = null;
        if (pass == null) return null;

        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(pass.getBytes(), 0, pass.length());
            encPass = new BigInteger(1, mDigest.digest()).toString(16);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return encPass;
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameText.getText();
        String password = passwordText.getText();
        // You can encrypt if needed:
        // String password = encryptPass(pass);

        // Example authentication — update according to your database logic
        if (new ConnectionFactory().checkLogin(username, password, "CUSTOMER")) {
            JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
            dispose();
            // Example next step:
            // new CustomerDashboard(username).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
        dispose();
        new CustomerProductSearch().setVisible(true);
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new LoginPage().setVisible(true);
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerLogin().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton backButton;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JTextField usernameText;
    private JButton registerButton;
    // End of variables declaration
}
