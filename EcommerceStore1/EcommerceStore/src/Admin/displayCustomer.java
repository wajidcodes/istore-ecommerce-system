package Admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class displayCustomer {

    JScrollPane customerPanel;
    JPanel contentScroll;
    JPanel mainPanel;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public displayCustomer() {

        contentScroll = new JPanel();
        contentScroll.setLayout(new BoxLayout(contentScroll, BoxLayout.Y_AXIS));
        contentScroll.setBackground(new Color(0xFAF9F6));
        showCustomers();

        customerPanel = new JScrollPane(contentScroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        customerPanel.setBounds(200, 50, 980, 590);
        customerPanel.getVerticalScrollBar().setUnitIncrement(20);
        customerPanel.setVisible(true);
    }

    public void showCustomers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String query = "SELECT * FROM signupdetail";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("FullName");
                String gender = rs.getString("Gender");
                String id = rs.getString("AccountType");
                String email = rs.getString("Email");
                String phone = rs.getString("PhoneNumber");

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

                JLabel nameLabel = new JLabel(name);
                nameLabel.setBounds(30, 10, 180, 30);
                singleCustomerPanel.add(nameLabel);

                JLabel idLabel = new JLabel(id);
                idLabel.setBounds(220, 10, 100, 30);
                singleCustomerPanel.add(idLabel);

                JLabel genderLabel = new JLabel(gender);
                genderLabel.setBounds(340, 10, 120, 30);
                singleCustomerPanel.add(genderLabel);

                JLabel emailLabel = new JLabel(email);
                emailLabel.setBounds(480, 10, 200, 30);
                singleCustomerPanel.add(emailLabel);

                JLabel phoneLabel = new JLabel(phone);
                phoneLabel.setBounds(700, 10, 150, 30);
                singleCustomerPanel.add(phoneLabel);

                contentScroll.add(singleCustomerPanel);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }

        contentScroll.revalidate();
        contentScroll.repaint();
    }
}
