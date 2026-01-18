package Admin;

import sDashboard.addProductPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class displaySupplier {

    JScrollPane supplierPanel;
    JPanel contentScroll;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public displaySupplier() {

        contentScroll = new JPanel();
        contentScroll.setLayout(new BoxLayout(contentScroll, BoxLayout.Y_AXIS));
        contentScroll.setBackground(new Color(0xFAF9F6));
        showSupplier();

        supplierPanel = new JScrollPane(contentScroll, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        supplierPanel.setBounds(200, 50, 980, 590);
        supplierPanel.getVerticalScrollBar().setUnitIncrement(20);
        supplierPanel.setVisible(true);
    }

    public void showSupplier() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String query = "SELECT * FROM supplieraccounts";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String storeName = rs.getString("StoreName");
                String Name = rs.getString("BusinessOwner");
                String ID = rs.getString("SupplierID");
                String CNIC = rs.getString("CNIC");
                String email = rs.getString("Email");
                String Address = rs.getString("Address");

                JPanel singleSupplierPanel = new JPanel();
                singleSupplierPanel.setLayout(new GridLayout(1, 6, 20, 20));
                singleSupplierPanel.setPreferredSize(new Dimension(920, 50));
                singleSupplierPanel.setMaximumSize(new Dimension(920, 50));
                singleSupplierPanel.setBackground(Color.WHITE);
                singleSupplierPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JCheckBox checkBox = new JCheckBox();
                checkBox.setBounds(5, 10, 20, 30);
                checkBox.setVerticalAlignment(SwingConstants.CENTER);
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setBackground(Color.WHITE);
                singleSupplierPanel.add(checkBox);

                JLabel nameLabel = new JLabel(Name);
                nameLabel.setBounds(40, 10, 180, 30);
                nameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                singleSupplierPanel.add(nameLabel);

                JLabel StoreLabel = new JLabel(storeName);
                StoreLabel.setBounds(240, 10, 100, 30);
                singleSupplierPanel.add(StoreLabel);

                JLabel sSupplierID = new JLabel(ID);
                sSupplierID.setBounds(260, 10, 100, 30);
                singleSupplierPanel.add(sSupplierID);

                JLabel sCNIC = new JLabel(CNIC);
                sCNIC.setBounds(380, 10, 180, 30);
                singleSupplierPanel.add(sCNIC);

                JLabel emailLabel = new JLabel(email);
                emailLabel.setBounds(600, 10, 180, 30);
                singleSupplierPanel.add(emailLabel);

                JLabel address = new JLabel(Address);
                address.setBounds(800, 10, 100, 30);
                singleSupplierPanel.add(address);

                contentScroll.add(singleSupplierPanel);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        contentScroll.revalidate();
        contentScroll.repaint();
    }
}
