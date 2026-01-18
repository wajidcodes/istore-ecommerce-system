package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ExploreProduct {

    JScrollPane exploreProductPanel;
    JPanel scrollContent;
    JPanel explorePanel;
    private C_Dashboard dashboard;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public ExploreProduct(String email, C_Dashboard dashboard) {

        this.dashboard = dashboard;

        explorePanel = new JPanel();
        explorePanel.setLayout(new GridLayout(0, 3, 20, 20));
        explorePanel.setBackground(Color.WHITE);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = """
                    SELECT *
                    FROM supplierproducts sp
                    JOIN supplierproductimages spi ON sp.ProductID = spi.ProductID
                    """;
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            int x = 20;
            int y = 70;
            while (rs.next()) {

                String title = rs.getString("ProductTitle");
                String description = rs.getString("Description");
                int priceP = rs.getInt("Price");
                int comparePrice = rs.getInt("ComparePrice");
                String size = rs.getString("Size");
                String weight = rs.getString("Weight");
                String ColorP = rs.getString("Color");
                int deliveryCost = rs.getInt("DeliveryCost");
                String supplierID = rs.getString("SupplierID");
                int quantity = rs.getInt("Quantity");

                ArrayList<byte[]> imageList = new ArrayList<>();

                Blob blob = rs.getBlob("Images");
                if (blob != null) {
                    byte[] bytes = blob.getBytes(1, (int) blob.length());
                    imageList.add(bytes);
                }

                JPanel productPanel = new JPanel();
                productPanel.setPreferredSize(new Dimension(300, 320));
                productPanel.setLayout(null);
                productPanel.setBackground(Color.WHITE);

                JLabel imageLabel = new JLabel();
                imageLabel.setBounds(10, 10, 280, 250);

                Blob imageBlob = rs.getBlob("Images");
                if (imageBlob != null) {
                    InputStream is = imageBlob.getBinaryStream();
                    byte[] imageBytes = is.readAllBytes();
                    ImageIcon icon = new ImageIcon(imageBytes);
                    Image img = icon.getImage().getScaledInstance(280, 250, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                }

                JLabel productTitle = new JLabel(title);
                productTitle.setBounds(10, 260, 280, 30);
                productTitle.setFont(new Font("Serif", Font.PLAIN, 20));
                productTitle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                JLabel price = new JLabel("Rs. " + priceP);
                price.setBounds(10, 290, 200, 30);
                price.setFont(new Font("Serif", Font.PLAIN, 20));

                productPanel.add(imageLabel);
                productPanel.add(productTitle);
                productPanel.add(price);

                explorePanel.add(productPanel);

                x += 300;
                if (x == 900) {
                    x = 0;
                    y += 300;
                }
                productTitle.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        ViewProduct obj = new ViewProduct(dashboard, imageList, title, description, priceP,
                                comparePrice, size, weight, ColorP, deliveryCost, supplierID, email, quantity);
                        dashboard.showProductPanel(obj);
                        obj.setVisible(true);
                    }

                });
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        explorePanel.revalidate();
        explorePanel.repaint();

        scrollContent = new JPanel();
        scrollContent.setLayout(new BorderLayout());
        scrollContent.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        scrollContent.add(explorePanel, BorderLayout.CENTER);
        scrollContent.setBackground(new Color(0xFAF9F6));

        exploreProductPanel = new JScrollPane(scrollContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        exploreProductPanel.getVerticalScrollBar().setUnitIncrement(20);
        exploreProductPanel.setBounds(200, 50, 980, 600);
        exploreProductPanel.setVisible(true);
    }

}
