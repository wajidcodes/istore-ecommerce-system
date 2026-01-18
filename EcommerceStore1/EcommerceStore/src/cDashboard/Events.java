package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Events extends JPanel {
    private final String DB_URL = "jdbc:mysql://localhost:3306/loginaccounts";
    private final String DB_USER = "root";
    private final String DB_PASS = "YOUR_MYSQL_PASSWORD_HERE";

    private JPanel eventsListPanel;

    public Events() {
        setLayout(new BorderLayout());
        setBounds(200, 50, 1000, 600);
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("All Events");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        eventsListPanel = new JPanel();
        eventsListPanel.setLayout(new BoxLayout(eventsListPanel, BoxLayout.Y_AXIS));
        eventsListPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(eventsListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        loadEventsFromDatabase();
    }

    private void loadEventsFromDatabase() {
        eventsListPanel.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String query = "SELECT supplier_id, title, description, event_date FROM events ORDER BY event_date DESC";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String supplierId = rs.getString("supplier_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");

                JPanel eventPanel = new JPanel(new BorderLayout());
                eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                eventPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
                eventPanel.setPreferredSize(new Dimension(900, 80));

                JLabel titleLabel = new JLabel(title + " (Supplier ID: " + supplierId + ")");
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

                JLabel dateLabel = new JLabel(eventDate.toString());
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));

                JTextArea descriptionArea = new JTextArea(description);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setEditable(false);
                descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
                descriptionArea.setBackground(Color.WHITE);
                descriptionArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                JPanel topPanel = new JPanel(new BorderLayout());
                topPanel.setBackground(Color.WHITE);
                topPanel.add(titleLabel, BorderLayout.WEST);
                topPanel.add(dateLabel, BorderLayout.EAST);

                eventPanel.add(topPanel, BorderLayout.NORTH);
                eventPanel.add(descriptionArea, BorderLayout.CENTER);

                eventsListPanel.add(eventPanel);
                eventsListPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            eventsListPanel.revalidate();
            eventsListPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load events: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Optional: test panel standalone
    public static void main(String[] args) {
        JFrame frame = new JFrame("All Events");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        Events panel = new Events();
        frame.add(panel);

        frame.setVisible(true);
    }
}
