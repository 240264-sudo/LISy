import  java.awt.*;
import javax.swing.*;

public class app {
    public static void main(String[] args) {
        // Create a new window
        JFrame frame = new JFrame("Local Inventory System - LISy");

        // Set the size of the window
        frame.setSize(400, 300);

        // Exit the application when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label
        JLabel label = new JLabel("LISy", SwingConstants.CENTER);
        // Increase label font size
        label.setFont(new  Font("Arial",  Font.BOLD, 32));

         // Create buttons
        JButton addButton = new JButton("Login As Buyer");
        JButton removeButton = new JButton("Login As Seller");

         // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // Set layout for frame
        frame.setLayout(new  GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new  Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        frame.add(label, gbc);

        gbc.gridy = 1;
        frame.add(buttonPanel, gbc);

        // Make the window visible
        frame.setVisible(true);
    }
}
