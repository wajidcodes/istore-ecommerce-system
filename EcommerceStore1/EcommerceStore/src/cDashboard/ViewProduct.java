package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ViewProduct extends JPanel {

    private JLabel imageLabel;
    private JLabel titleLabel;
    private JTextArea descriptionArea;
    private JLabel priceLabel;
    private JLabel comparePriceLabel;
    private JLabel sizeLabel;
    private JLabel weightLabel;
    private JLabel colorLabel;
    private JLabel deliveryCostLabel;
    private JButton addToCartButton;
    private JButton buyNowButton;
    JLabel quantityLabel;
    JTextField quantity;

    private C_Dashboard dashboard;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public ViewProduct(C_Dashboard dashboard, ArrayList<byte[]> images, String title, String description, int price,
            int comparePrice,
            String size, String weight, String color, int deliveryCost, String supplierID, String email, int Quantity) {

        setLayout(null);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(980, 600));

        this.dashboard = dashboard;

        // Image
        imageLabel = new JLabel();
        imageLabel.setBounds(20, 20, 300, 300);
        if (!images.isEmpty()) {
            ImageIcon icon = new ImageIcon(images.get(0));
            Image img = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
        }
        add(imageLabel);

        // Title
        titleLabel = new JLabel(title);
        titleLabel.setBounds(340, 20, 600, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);

        // Description
        descriptionArea = new JTextArea(description);
        descriptionArea.setBounds(340, 60, 600, 100);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        add(descriptionArea);

        // Price Info
        priceLabel = new JLabel("Price: Rs. " + price);
        priceLabel.setBounds(340, 170, 300, 30);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(priceLabel);

        comparePriceLabel = new JLabel("Compare Price: Rs. " + comparePrice);
        comparePriceLabel.setBounds(340, 200, 300, 30);
        comparePriceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(comparePriceLabel);

        // Attributes
        sizeLabel = new JLabel("Size: " + size);
        sizeLabel.setBounds(340, 240, 300, 20);
        add(sizeLabel);

        weightLabel = new JLabel("Weight: " + weight);
        weightLabel.setBounds(340, 260, 300, 20);
        add(weightLabel);

        colorLabel = new JLabel("Color: " + color);
        colorLabel.setBounds(340, 280, 300, 20);
        add(colorLabel);

        // ---- Quantity
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(440, 260, 80, 30);
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        add(quantityLabel);

        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");
        minusButton.setBounds(535, 260, 50, 30);
        minusButton.setFocusable(false);
        minusButton.setBackground(new Color(0xFAF9F6));
        minusButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(minusButton);

        quantity = new JTextField("1");
        quantity.setBounds(590, 260, 80, 30);
        quantity.setBackground(new Color(0xFAF9F6));
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        add(quantity);

        plusButton.setBounds(675, 260, 50, 30);
        plusButton.setFocusable(false);
        plusButton.setBackground(new Color(0xFAF9F6));
        plusButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(plusButton);

        minusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int text = Integer.parseInt(quantity.getText());
                if (text > 1) {
                    text--;
                    quantity.setText(String.valueOf(text));
                } else {
                    quantity.setText("1");
                }
            }
        });

        plusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int text = Integer.parseInt(quantity.getText());
                if (text > 1) {
                    text++;
                    quantity.setText(String.valueOf(text));
                } else {
                    quantity.setText("1");
                }
            }
        });

        deliveryCostLabel = new JLabel("Delivery Cost: Rs. " + deliveryCost);
        deliveryCostLabel.setBounds(340, 300, 300, 20);
        add(deliveryCostLabel);

        // Buttons
        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBounds(340, 350, 150, 40);
        add(addToCartButton);

        int q = Integer.parseInt(quantity.getText());
        int pr = price;
        int total = q * pr;

        addToCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println(e1.getMessage());
                }
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    String query = "INSERT INTO mycarts (`CustomerEmail`, `Title`, `Price`, `SupplierID`, DeliveryCost,Quantity, Total) VALUES (?, ?, ?, ?, ?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, title);
                    preparedStatement.setInt(3, price);
                    preparedStatement.setString(4, supplierID);
                    preparedStatement.setInt(5, deliveryCost);
                    preparedStatement.setInt(6, q);
                    preparedStatement.setInt(7, total);

                    int result = preparedStatement.executeUpdate();
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Product Added Successfully");
            }

        });

        buyNowButton = new JButton("Buy Now");
        buyNowButton.setBounds(510, 350, 150, 40);
        add(buyNowButton);

        buyNowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlaceOrder placeOrder = new PlaceOrder(title, q, supplierID, deliveryCost, email);
                dashboard.showPlaceOrderPanel(placeOrder);

            }
        });
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }
}
