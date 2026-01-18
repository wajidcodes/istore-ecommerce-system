package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CartPanel extends JPanel {

    private C_Dashboard dashboard;
    private String userEmail;
    private JPanel itemsPanel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JButton checkoutButton;
    private JLabel totalLabel;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public CartPanel(String email, C_Dashboard dashboard) {
        this.dashboard = dashboard;
        this.userEmail = email;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(980, 600));

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        backButton = new JButton("Back");
        backButton.addActionListener(e -> dashboard.exploreProduct.exploreProductPanel.setVisible(true));
        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Your Shopping Cart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Items panel
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        totalLabel = new JLabel("Total: Rs. 0", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        footerPanel.add(totalLabel, BorderLayout.CENTER);

        checkoutButton = new JButton("Checkout Now");
        checkoutButton.setPreferredSize(new Dimension(150, 40));
        checkoutButton.addActionListener(e -> proceedToCheckout());
        footerPanel.add(checkoutButton, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);

        loadCartItems();
    }

    public void loadCartItems() {
        itemsPanel.removeAll();
        int totalAmount = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            // Get cart items for this user
            String query = "SELECT c.*, p.Images FROM mycarts c JOIN products p ON c.Title = p.Title WHERE c.CustomerEmail = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                int price = resultSet.getInt("Price");
                int quantity = resultSet.getInt("Quantity");
                int deliveryCost = resultSet.getInt("DeliveryCost");
                int itemTotal = resultSet.getInt("Total");
                byte[] imageBytes = resultSet.getBytes("Images");

                totalAmount += itemTotal;

                // Create cart item panel
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

                // Image
                JLabel imageLabel = new JLabel();
                if (imageBytes != null) {
                    ImageIcon icon = new ImageIcon(imageBytes);
                    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                }
                itemPanel.add(imageLabel, BorderLayout.WEST);

                // Info panel
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                infoPanel.setBackground(Color.WHITE);

                JLabel titleLabel = new JLabel(title);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                infoPanel.add(titleLabel);

                infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

                JLabel priceLabel = new JLabel("Price: Rs. " + price);
                infoPanel.add(priceLabel);

                JLabel quantityLabel = new JLabel("Quantity: " + quantity);
                infoPanel.add(quantityLabel);

                JLabel deliveryLabel = new JLabel("Delivery: Rs. " + deliveryCost);
                infoPanel.add(deliveryLabel);

                JLabel totalLabel = new JLabel("Total: Rs. " + itemTotal);
                totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
                infoPanel.add(totalLabel);

                itemPanel.add(infoPanel, BorderLayout.CENTER);

                // Button panel
                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                buttonPanel.setBackground(Color.WHITE);

                JButton removeButton = new JButton("Remove");
                removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                removeButton.addActionListener(e -> removeItem(title));

                JButton orderNowButton = new JButton("Order Now");
                orderNowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                orderNowButton.addActionListener(e -> orderItemNow(title, price, quantity, deliveryCost, itemTotal));

                buttonPanel.add(removeButton);
                buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                buttonPanel.add(orderNowButton);

                itemPanel.add(buttonPanel, BorderLayout.EAST);

                itemsPanel.add(itemPanel);
                itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            totalLabel.setText("Total: Rs. " + totalAmount);

            if (itemsPanel.getComponentCount() == 0) {
                JLabel emptyLabel = new JLabel("Your cart is empty", SwingConstants.CENTER);
                emptyLabel.setFont(new Font("Arial", Font.ITALIC, 18));
                itemsPanel.add(emptyLabel);
                checkoutButton.setEnabled(false);
            } else {
                checkoutButton.setEnabled(true);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading cart items: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private void removeItem(String title) {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "DELETE FROM mycarts WHERE CustomerEmail = ? AND Title = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, title);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Item removed from cart");
                loadCartItems(); // Refresh the cart
            }

            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error removing item: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void orderItemNow(String title, int price, int quantity, int deliveryCost, int total) {
        // Implement single item checkout
        PlaceOrder placeOrder = new PlaceOrder(title, quantity, "", deliveryCost, userEmail);
        dashboard.showPlaceOrderPanel(placeOrder);
    }

    private void proceedToCheckout() {

        PlaceOrder placeOrder = new PlaceOrder("All Cart Items", 1, "", 0, userEmail);
        dashboard.showPlaceOrderPanel(placeOrder);
    }

    public void refreshCart() {
        loadCartItems();
    }
}