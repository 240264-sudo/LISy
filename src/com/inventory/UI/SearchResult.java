package com.inventory.UI;

import com.inventory.Database.ConnectionFactory;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * SearchResult frame — displays store map and product table filtered by location
 */
public class SearchResult extends javax.swing.JFrame {

    private MapPanel mapPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable resultTable;
    private javax.swing.JLabel titleLabel;
    private DefaultTableModel tableModel;
    private java.util.List<Store> storeList = new ArrayList<>();

    public SearchResult(String item, String location) {
        initComponents();
        loadResults(item, location);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        mapPanel = new MapPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Results");
        setBounds(new java.awt.Rectangle(300, 100, 0, 0));
        setName("searchResultFrame");

        titleLabel.setFont(new java.awt.Font("Poor Richard", 1, 28)); 
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Search Results");

        mapPanel.setBackground(Color.WHITE);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        mapPanel.setPreferredSize(new java.awt.Dimension(900, 400));

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Product Name", "Price", "Stock", "Location"}
        ));
        jScrollPane1.setViewportView(resultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addComponent(mapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(mapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    // Load data from DB and update UI
    private void loadResults(String searchItem, String searchLocation) {
        tableModel = (DefaultTableModel) resultTable.getModel();
        tableModel.setRowCount(0);
        storeList.clear();

        String query = """
            SELECT p.product_name, p.price, p.stock, s.location, s.x_coordinate, s.y_coordinate
            FROM products p
            JOIN stores s ON p.store_id = s.store_id
            WHERE p.product_name LIKE ? AND s.location LIKE ?;
        """;

        try (Connection conn = new ConnectionFactory().getConn();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, "%" + searchItem + "%");
            pst.setString(2, "%" + searchLocation + "%");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock");
                String loc = rs.getString("location");
                int x = rs.getInt("x_coordinate");
                int y = rs.getInt("y_coordinate");

                tableModel.addRow(new Object[]{name, price, stock, loc});
                storeList.add(new Store(x, y, loc));
            }

            if (storeList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No products found in this location.");
            }

            mapPanel.setStores(storeList);
            mapPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    // Custom map panel with scaled coordinate drawing
    private class MapPanel extends JPanel {
        private java.util.List<Store> stores = new ArrayList<>();

        public void setStores(java.util.List<Store> stores) {
            this.stores = stores;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (stores == null || stores.isEmpty()) return;

            int width = getWidth();
            int height = getHeight();

            int minX = stores.stream().mapToInt(s -> s.x).min().orElse(0);
            int maxX = stores.stream().mapToInt(s -> s.x).max().orElse(1);
            int minY = stores.stream().mapToInt(s -> s.y).min().orElse(0);
            int maxY = stores.stream().mapToInt(s -> s.y).max().orElse(1);

            for (Store s : stores) {
                int scaledX = (int) ((s.x - minX) / (double) (maxX - minX + 1) * (width - 40)) + 20;
                int scaledY = (int) ((s.y - minY) / (double) (maxY - minY + 1) * (height - 40)) + 20;

                g.setColor(Color.RED);
                g.fillOval(scaledX - 5, scaledY - 5, 10, 10);
                g.setColor(Color.BLACK);
                g.drawString(s.location, scaledX + 10, scaledY);
            }
        }
    }

    class Store {
        int x, y;
        String location;
        Store(int x, int y, String location) {
            this.x = x;
            this.y = y;
            this.location = location;
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new SearchResult("Laptop", "Kollam").setVisible(true);
        });
    }
}
