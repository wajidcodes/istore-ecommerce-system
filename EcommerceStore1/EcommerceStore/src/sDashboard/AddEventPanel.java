package sDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddEventPanel extends JPanel {

    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField dateField; // Format: yyyy-MM-dd
    private JButton submitButton;
    private JLabel statusLabel;

    private String supplierId;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/loginaccounts";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "YOUR_MYSQL_PASSWORD_HERE";

    public AddEventPanel(String supplierId) {
        this.supplierId = supplierId;
        initComponents();
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(200, 50, 1000, 600);
    }

    private void initComponents() {
        JLabel titleLabel = new JLabel("Event/Sale Title:");
        titleLabel.setBounds(50, 30, 150, 25);

        titleField = new JTextField();
        titleField.setBounds(200, 30, 300, 25);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(50, 70, 150, 25);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descScroll = new JScrollPane(descriptionArea);
        descScroll.setBounds(200, 70, 300, 100);

        JLabel dateLabel = new JLabel("Date (yyyy-MM-dd):");
        dateLabel.setBounds(50, 190, 150, 25);

        dateField = new JTextField();
        dateField.setBounds(200, 190, 150, 25);

        submitButton = new JButton("Add Event");
        submitButton.setBounds(200, 240, 150, 30);

        statusLabel = new JLabel("");
        statusLabel.setBounds(50, 290, 450, 25);
        statusLabel.setForeground(Color.RED);

        add(titleLabel);
        add(titleField);
        add(descLabel);
        add(descScroll);
        add(dateLabel);
        add(dateField);
        add(submitButton);
        add(statusLabel);

        submitButton.addActionListener(e -> addEventToDatabase());
    }

    private void addEventToDatabase() {
        String title = titleField.getText().trim();
        String description = descriptionArea.getText().trim();
        String date = dateField.getText().trim();

        if (title.isEmpty() || description.isEmpty() || date.isEmpty()) {
            statusLabel.setText("Please fill in all fields.");
            return;
        }

        // Simple date format validation (basic)
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            statusLabel.setText("Date format must be yyyy-MM-dd");
            return;
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO events (supplier_id, title, description, event_date) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, supplierId);
            pst.setString(2, title);
            pst.setString(3, description);
            pst.setDate(4, Date.valueOf(date));

            int rows = pst.executeUpdate();
            if (rows > 0) {
                statusLabel.setForeground(new Color(0, 128, 0)); // Green
                statusLabel.setText("Event added successfully!");
                clearFields();
            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Failed to add event.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            statusLabel.setForeground(Color.RED);
            statusLabel.setText("Database error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        titleField.setText("");
        descriptionArea.setText("");
        dateField.setText("");
    }
}
