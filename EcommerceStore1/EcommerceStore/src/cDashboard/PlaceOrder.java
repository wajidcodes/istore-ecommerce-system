package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PlaceOrder extends JPanel {

    private JLabel lblTitle, lblPrice, lblSupplierID, lblDeliveryCost;
    private JTextField tfFullName, tfEmail, tfAddress, tfCity, tfProvince, tfPostalCode;
    private JButton btnPlaceOrder;

    private String productTitle;
    private int price;
    private String supplierID;
    private int deliveryCost;
    private String userEmail;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public PlaceOrder(String productTitle, int price, String supplierID, int deliveryCost, String userEmail) {
        this.productTitle = productTitle;
        this.price = price;
        this.supplierID = supplierID;
        this.deliveryCost = deliveryCost;
        this.userEmail = userEmail;

        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(200, 50, 980, 600);

        initComponents();
    }

    private void initComponents() {
        // Product details labels (non-editable)
        lblTitle = new JLabel("Product: " + productTitle);
        lblPrice = new JLabel("Price: Rs. " + price);
        lblSupplierID = new JLabel("Supplier: " + supplierID);
        lblDeliveryCost = new JLabel("Delivery Cost: Rs. " + deliveryCost);

        lblTitle.setBounds(50, 20, 500, 30);
        lblPrice.setBounds(50, 60, 200, 30);
        lblSupplierID.setBounds(50, 100, 200, 30);
        lblDeliveryCost.setBounds(50, 140, 200, 30);

        add(lblTitle);
        add(lblPrice);
        add(lblSupplierID);
        add(lblDeliveryCost);

        // Input fields for user info
        JLabel lblFullName = new JLabel("Full Name:");
        tfFullName = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        tfEmail = new JTextField();

        JLabel lblAddress = new JLabel("Address:");
        tfAddress = new JTextField();

        JLabel lblCity = new JLabel("City:");
        tfCity = new JTextField();

        JLabel lblProvince = new JLabel("Province:");
        tfProvince = new JTextField();

        JLabel lblPostalCode = new JLabel("Postal Code:");
        tfPostalCode = new JTextField();

        int labelX = 50, fieldX = 150, width = 300, height = 25;
        int y = 200, yGap = 40;

        lblFullName.setBounds(labelX, y, 100, height);
        tfFullName.setBounds(fieldX, y, width, height);
        y += yGap;

        lblEmail.setBounds(labelX, y, 100, height);
        tfEmail.setBounds(fieldX, y, width, height);
        y += yGap;

        lblAddress.setBounds(labelX, y, 100, height);
        tfAddress.setBounds(fieldX, y, width, height);
        y += yGap;

        lblCity.setBounds(labelX, y, 100, height);
        tfCity.setBounds(fieldX, y, width, height);
        y += yGap;

        lblProvince.setBounds(labelX, y, 100, height);
        tfProvince.setBounds(fieldX, y, width, height);
        y += yGap;

        lblPostalCode.setBounds(labelX, y, 100, height);
        tfPostalCode.setBounds(fieldX, y, width, height);
        y += yGap;

        add(lblFullName);
        add(tfFullName);
        add(lblEmail);
        add(tfEmail);
        add(lblAddress);
        add(tfAddress);
        add(lblCity);
        add(tfCity);
        add(lblProvince);
        add(tfProvince);
        add(lblPostalCode);
        add(tfPostalCode);

        // Place Order button
        btnPlaceOrder = new JButton("Place Order");
        btnPlaceOrder.setBounds(fieldX, y + 20, 150, 30);
        add(btnPlaceOrder);

        // Button action (currently just shows a confirmation dialog)
        btnPlaceOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        String fullName = tfFullName.getText().trim();
        String email = tfEmail.getText().trim();
        String address = tfAddress.getText().trim();
        String city = tfCity.getText().trim();
        String province = tfProvince.getText().trim();
        String postalCode = tfPostalCode.getText().trim();

        if (fullName.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() || province.isEmpty()
                || postalCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO orders "
                    + "(user_email, product_title, price, supplier_id, delivery_cost, full_name, email, address, city, province, postal_code) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userEmail);
            ps.setString(2, productTitle);
            ps.setInt(3, price);
            ps.setString(4, supplierID);
            ps.setInt(5, deliveryCost);
            ps.setString(6, fullName);
            ps.setString(7, email);
            ps.setString(8, address);
            ps.setString(9, city);
            ps.setString(10, province);
            ps.setString(11, postalCode);

            int result = ps.executeUpdate();

            if (result > 0) {
                JOptionPane.showMessageDialog(this,
                        "Order placed successfully for " + productTitle + "!\nThank you, " + fullName + ".");
                // You can clear fields or navigate to another panel here
            } else {
                JOptionPane.showMessageDialog(this, "Failed to place order.");
            }

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

}
