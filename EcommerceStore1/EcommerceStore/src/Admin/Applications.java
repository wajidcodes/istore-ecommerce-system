package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Applications {

    JScrollPane APanel;
    JPanel contentScroll;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/admin";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public Applications() {
        contentScroll = new JPanel();
        contentScroll.setLayout(new BoxLayout(contentScroll, BoxLayout.Y_AXIS));
        contentScroll.setBackground(new Color(0xFAF9F6));
        showCustomers();

        APanel = new JScrollPane(contentScroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        APanel.setBounds(200, 50, 980, 590);
        APanel.getVerticalScrollBar().setUnitIncrement(20);
        APanel.setVisible(true);
    }

    public void showCustomers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String query = "SELECT * FROM applications";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String from = rs.getString("From");

                JPanel singleCustomerPanel = new JPanel();
                singleCustomerPanel.setLayout(new GridLayout(1, 5, 20, 20));
                singleCustomerPanel.setPreferredSize(new Dimension(920, 50));
                singleCustomerPanel.setMaximumSize(new Dimension(920, 50));
                singleCustomerPanel.setBackground(Color.WHITE);
                singleCustomerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JCheckBox checkBox = new JCheckBox();
                checkBox.setBounds(5, 10, 20, 30);
                checkBox.setBackground(Color.WHITE);
                singleCustomerPanel.add(checkBox);

                JLabel nameLabel = new JLabel(title);
                nameLabel.setBounds(30, 10, 250, 30);
                singleCustomerPanel.add(nameLabel);

                JLabel idLabel = new JLabel(description);
                idLabel.setBounds(320, 10, 250, 30);
                singleCustomerPanel.add(idLabel);

                JLabel genderLabel = new JLabel(from);
                genderLabel.setBounds(700, 10, 100, 30);
                singleCustomerPanel.add(genderLabel);

                contentScroll.add(singleCustomerPanel);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        contentScroll.revalidate();
        contentScroll.repaint();
    }
}
