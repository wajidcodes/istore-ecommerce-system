package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerNotifySupplierPanel extends JPanel {
    private JTextField supplierIdField;
    private JTextArea messageArea;
    private JButton sendButton;
    private final String DB_URL = "jdbc:mysql://localhost:3306/loginaccounts";
    private final String DB_USER = "root";
    private final String DB_PASS = "YOUR_MYSQL_PASSWORD_HERE";

    private String customerId; // Pass current logged-in customer ID here

    public CustomerNotifySupplierPanel(String customerId) {
        this.customerId = customerId;

        setLayout(null);
        setBounds(200, 50, 1000, 600);
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Send Notification to Supplier");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(50, 20, 500, 30);
        add(titleLabel);

        JLabel supplierIdLabel = new JLabel("Supplier ID:");
        supplierIdLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        supplierIdLabel.setBounds(50, 80, 100, 25);
        add(supplierIdLabel);

        supplierIdField = new JTextField();
        supplierIdField.setBounds(150, 80, 200, 30);
        add(supplierIdField);

        JLabel messageLabel = new JLabel("Message:");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setBounds(50, 130, 100, 25);
        add(messageLabel);

        messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane messageScroll = new JScrollPane(messageArea);
        messageScroll.setBounds(150, 130, 600, 250);
        add(messageScroll);

        sendButton = new JButton("Send Notification");
        sendButton.setBounds(150, 400, 200, 40);
        sendButton.setBackground(new Color(59, 89, 182));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        add(sendButton);

        sendButton.addActionListener(e -> sendNotification());
    }

    private void sendNotification() {
        String supplierId = supplierIdField.getText().trim();
        String message = messageArea.getText().trim();

        if (supplierId.isEmpty() || message.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String insertQuery = "INSERT INTO notifications (supplier_id, customer_id, message) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(insertQuery);
            pst.setString(1, supplierId);
            pst.setString(2, customerId);
            pst.setString(3, message);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Notification sent successfully.");
                supplierIdField.setText("");
                messageArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to send notification.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
