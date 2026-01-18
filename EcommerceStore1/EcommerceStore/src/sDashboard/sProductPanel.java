package sDashboard;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Objects;

public class sProductPanel extends JPanel {
    JScrollPane productPanel;
    JPanel scrollContentPanel;
    JPanel pBarPanel;
    JPanel pProductBarPanel;
    JPanel pProductPanel;
    JLabel pDaysLabel;
    JLabel heading1Label;
    JLabel data1Label;
    JLabel heading2Label;
    JLabel data2Label;
    JLabel heading3Label;
    JLabel data3Label;
    JLabel allLabel;
    JLabel activeLabel;
    JLabel draftLabel;
    JLabel archivedLabel;
    JButton addProductButton;
    JLabel sortLabel;
    JCheckBox selectAll;
    JLabel productName;
    JLabel productStatus;
    JLabel inventory;
    JLabel catagoryName;
    JLabel vendorName;
    JLabel productPrice;
    String supplierID;
    JPanel productsShowPanel;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public sProductPanel(String supplierID) {
        this.supplierID = supplierID;

        productsShowPanel = new JPanel();
        productsShowPanel.setPreferredSize(new Dimension(1300, 600));
        productsShowPanel.setBackground(new Color(0xFAF9F6));
        showProduct();

        // ---Product panel Products starting Bar---
        vendorName = new JLabel("Vendor");
        vendorName.setBounds(810, 7, 100, 20);
        vendorName.setFont(new Font("Roboto", Font.PLAIN, 12));
        vendorName.setVerticalAlignment(SwingConstants.CENTER);
        vendorName.setForeground(Color.BLACK);

        productPrice = new JLabel("Price");
        productPrice.setBounds(700, 7, 100, 20);
        productPrice.setFont(new Font("Roboto", Font.PLAIN, 12));
        productPrice.setVerticalAlignment(SwingConstants.CENTER);
        productPrice.setForeground(Color.BLACK);

        catagoryName = new JLabel("Catagory");
        catagoryName.setBounds(490, 7, 200, 20);
        catagoryName.setFont(new Font("Roboto", Font.PLAIN, 12));
        catagoryName.setVerticalAlignment(SwingConstants.CENTER);
        catagoryName.setForeground(Color.BLACK);

        inventory = new JLabel("Inventory");
        inventory.setBounds(380, 7, 100, 20);
        inventory.setFont(new Font("Roboto", Font.PLAIN, 12));
        inventory.setVerticalAlignment(SwingConstants.CENTER);
        inventory.setForeground(Color.BLACK);

        productStatus = new JLabel("Status");
        productStatus.setBounds(280, 7, 90, 20);
        productStatus.setFont(new Font("Roboto", Font.PLAIN, 12));
        productStatus.setVerticalAlignment(SwingConstants.CENTER);
        productStatus.setForeground(Color.BLACK);

        productName = new JLabel("Products");
        productName.setBounds(30, 7, 240, 20);
        productName.setFont(new Font("Roboto", Font.PLAIN, 12));
        productName.setVerticalAlignment(SwingConstants.CENTER);
        productName.setForeground(Color.BLACK);

        selectAll = new JCheckBox();
        selectAll.setSelected(false);
        selectAll.setBounds(5, 7, 20, 20);
        selectAll.setFocusable(false);
        selectAll.setBackground(Color.WHITE);
        selectAll.setForeground(Color.black);
        selectAll.setVerticalAlignment(SwingConstants.CENTER);
        selectAll.setHorizontalAlignment(SwingConstants.CENTER);

        pProductPanel = new JPanel();
        pProductPanel.setPreferredSize(new Dimension(980, 30));
        pProductPanel.setBackground(new Color(0xFAF9F6));
        pProductPanel.add(selectAll);
        pProductPanel.add(productName);
        pProductPanel.add(productStatus);
        pProductPanel.add(inventory);
        pProductPanel.add(catagoryName);
        pProductPanel.add(productPrice);
        pProductPanel.add(vendorName);
        pProductPanel.setLayout(null);

        // ---Product panel Product bar---
        sortLabel = new JLabel();
        ImageIcon sortIcon = new ImageIcon(ClassLoader.getSystemResource("images/sort.png"));
        Image sortimage = sortIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon sortImage1 = new ImageIcon(sortimage);
        sortLabel.setIcon(sortImage1);
        sortLabel.setBounds(930, 5, 30, 30);
        sortLabel.setLayout(null);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(780, 5, 130, 30);
        addProductButton.setFont(new Font("Roboto", Font.BOLD, 14));
        addProductButton.setBackground(Color.BLACK);
        addProductButton.setForeground(Color.WHITE);
        addProductButton.setOpaque(true);
        addProductButton.setHorizontalAlignment(SwingConstants.CENTER);
        addProductButton.setVerticalAlignment(SwingConstants.CENTER);
        addProductButton.setFocusable(false);
        addProductButton.setVisible(true);

        archivedLabel = new JLabel("Archived");
        archivedLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        archivedLabel.setBounds(140, 5, 60, 30);
        archivedLabel.setForeground(Color.BLACK);
        archivedLabel.setHorizontalAlignment(SwingConstants.CENTER);
        archivedLabel.setVerticalAlignment(SwingConstants.CENTER);
        archivedLabel.setBackground(Color.lightGray);
        archivedLabel.setLayout(null);
        archivedLabel.setOpaque(true);
        archivedLabel.setVisible(true);

        draftLabel = new JLabel("Draft");
        draftLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        draftLabel.setBounds(90, 5, 45, 30);
        draftLabel.setHorizontalAlignment(SwingConstants.CENTER);
        draftLabel.setVerticalAlignment(SwingConstants.CENTER);
        draftLabel.setForeground(Color.BLACK);
        draftLabel.setBackground(Color.lightGray);
        draftLabel.setOpaque(true);

        activeLabel = new JLabel("Active");
        activeLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        activeLabel.setBounds(40, 5, 45, 30);
        activeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        activeLabel.setVerticalAlignment(SwingConstants.CENTER);
        activeLabel.setForeground(Color.BLACK);
        activeLabel.setBackground(Color.lightGray);
        activeLabel.setOpaque(true);

        allLabel = new JLabel("All");
        allLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        allLabel.setBounds(5, 5, 30, 30);
        allLabel.setHorizontalAlignment(SwingConstants.CENTER);
        allLabel.setVerticalAlignment(SwingConstants.CENTER);
        allLabel.setForeground(Color.BLACK);
        allLabel.setBackground(Color.lightGray);
        allLabel.setOpaque(true);

        pProductBarPanel = new JPanel();
        pProductBarPanel.setPreferredSize(new Dimension(960, 40));
        pProductBarPanel.setLayout(null);
        pProductBarPanel.setBackground(Color.WHITE);
        pProductBarPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pProductBarPanel.add(allLabel);
        pProductBarPanel.add(activeLabel);
        pProductBarPanel.add(draftLabel);
        pProductBarPanel.add(archivedLabel);
        pProductBarPanel.add(addProductButton);
        pProductBarPanel.add(sortLabel);
        pProductBarPanel.setVisible(true);

        // ---Product panel Bar---
        data3Label = new JLabel("No Data");
        data3Label.setBounds(760, 30, 100, 30);
        data3Label.setForeground(Color.lightGray);
        data3Label.setFont(new Font("Roboto", Font.PLAIN, 14));
        data3Label.setHorizontalAlignment(SwingConstants.LEFT);
        data3Label.setVerticalAlignment(SwingConstants.BOTTOM);

        heading3Label = new JLabel("ABC product analysis");
        heading3Label.setFont(new Font("Roboto", Font.PLAIN, 16));
        heading3Label.setBounds(760, 10, 300, 40);
        heading3Label.setForeground(Color.BLACK);
        heading3Label.setHorizontalAlignment(SwingConstants.LEFT);
        heading3Label.setVerticalAlignment(SwingConstants.CENTER);

        data2Label = new JLabel("No Data");
        data2Label.setBounds(440, 30, 100, 30);
        data2Label.setForeground(Color.lightGray);
        data2Label.setFont(new Font("Roboto", Font.PLAIN, 14));
        data2Label.setHorizontalAlignment(SwingConstants.LEFT);
        data2Label.setVerticalAlignment(SwingConstants.BOTTOM);

        heading2Label = new JLabel("Product by day of inventory");
        heading2Label.setFont(new Font("Roboto", Font.PLAIN, 16));
        heading2Label.setBounds(440, 10, 300, 40);
        heading2Label.setForeground(Color.BLACK);
        heading2Label.setHorizontalAlignment(SwingConstants.LEFT);
        heading2Label.setVerticalAlignment(SwingConstants.CENTER);

        data1Label = new JLabel("No Data");
        data1Label.setBounds(120, 30, 100, 30);
        data1Label.setForeground(Color.lightGray);
        data1Label.setFont(new Font("Roboto", Font.PLAIN, 14));
        data1Label.setHorizontalAlignment(SwingConstants.LEFT);
        data1Label.setVerticalAlignment(SwingConstants.BOTTOM);

        heading1Label = new JLabel("Average sell -through rate");
        heading1Label.setFont(new Font("Roboto", Font.PLAIN, 16));
        heading1Label.setBounds(120, 10, 300, 40);
        heading1Label.setForeground(Color.BLACK);
        heading1Label.setHorizontalAlignment(SwingConstants.LEFT);
        heading1Label.setVerticalAlignment(SwingConstants.CENTER);

        pDaysLabel = new JLabel("30 Days");
        pDaysLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
        pDaysLabel.setBounds(10, 10, 100, 50);
        ImageIcon imagebag = new ImageIcon(ClassLoader.getSystemResource("images/bagofProductPanel.png"));
        Image image1 = imagebag.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon image2 = new ImageIcon(image1);
        pDaysLabel.setIcon(image2);
        pDaysLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        pDaysLabel.setVerticalTextPosition(SwingConstants.CENTER);
        pDaysLabel.setForeground(Color.BLACK);

        pBarPanel = new JPanel();
        pBarPanel.setPreferredSize(new Dimension(960, 70));
        pBarPanel.setBackground(Color.WHITE);
        pBarPanel.setLayout(null);
        pBarPanel.add(pDaysLabel);
        pBarPanel.add(heading1Label);
        pBarPanel.add(data1Label);
        pBarPanel.add(heading2Label);
        pBarPanel.add(data2Label);
        pBarPanel.add(heading3Label);
        pBarPanel.add(data3Label);
        pBarPanel.setVisible(true);

        // ----panel of products----
        scrollContentPanel = new JPanel();
        scrollContentPanel.setLayout(new BoxLayout(scrollContentPanel, BoxLayout.Y_AXIS));
        scrollContentPanel.setBackground(new Color(0xFAF9F6));
        scrollContentPanel.add(pBarPanel);
        scrollContentPanel.add(Box.createVerticalStrut(20));
        scrollContentPanel.add(pProductBarPanel);
        scrollContentPanel.add(Box.createVerticalStrut(5));
        scrollContentPanel.add(pProductPanel);
        scrollContentPanel.add(Box.createVerticalStrut(5));
        scrollContentPanel.add(productsShowPanel);
        scrollContentPanel.add(Box.createVerticalStrut(20));

        productPanel = new JScrollPane();
        productPanel.setViewportView(scrollContentPanel);
        productPanel.setBounds(200, 50, 985, 600);
        productPanel.setBackground(new Color(0xFAF9F6));
        productPanel.getVerticalScrollBar().setUnitIncrement(20);
    }

    public void showProduct() {
        productsShowPanel.removeAll();
        productsShowPanel.setLayout(new BoxLayout(productsShowPanel, BoxLayout.Y_AXIS));
        int y = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String query = "SELECT * FROM supplierproducts";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductTitle");
                String status = rs.getString("Status");
                String catagory = rs.getString("Catagory");
                int productPrice = Integer.parseInt(rs.getString("Price"));
                String supplier = rs.getString("SupplierID");

                if (supplierID.equals(supplier)) {
                    JPanel singleProductPanel = new JPanel(new GridLayout(1, 7));
                    singleProductPanel.setLayout(null);
                    singleProductPanel.setPreferredSize(new Dimension(920, 50));
                    singleProductPanel.setMaximumSize(new Dimension(920, 50));
                    singleProductPanel.setAlignmentX(10);
                    singleProductPanel.setAlignmentY(y);
                    singleProductPanel.setBackground(Color.WHITE);
                    singleProductPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setBounds(5, 10, 20, 30);
                    checkBox.setVerticalAlignment(SwingConstants.CENTER);
                    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                    checkBox.setBackground(Color.WHITE);
                    singleProductPanel.add(checkBox);

                    JLabel productLabel = new JLabel(productName);
                    productLabel.setBounds(30, 10, 240, 30);
                    productLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    singleProductPanel.add(productLabel);
                    productLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            view(productId);
                        }
                    });

                    JLabel statusLabel = new JLabel(status);
                    statusLabel.setBounds(280, 10, 90, 30);
                    singleProductPanel.add(statusLabel);

                    JLabel inventory = new JLabel("Inventory");
                    inventory.setBounds(380, 10, 100, 30);
                    singleProductPanel.add(inventory);

                    JLabel catag = new JLabel(catagory);
                    catag.setBounds(490, 10, 200, 30);
                    singleProductPanel.add(catag);

                    JLabel priceLabel = new JLabel("Rs. " + productPrice);
                    priceLabel.setBounds(700, 10, 100, 30);
                    singleProductPanel.add(priceLabel);

                    JLabel vendor = new JLabel(supplierID);
                    vendor.setBounds(810, 10, 100, 30);
                    singleProductPanel.add(vendor);

                    productsShowPanel.add(singleProductPanel);
                    y += 51;
                }
            }

            productsShowPanel.revalidate();
            productsShowPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void view(int productId) {

        addProductPanel obj = new addProductPanel(supplierID);

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement st = connection.createStatement();
            String query = """
                    SELECT *
                    FROM supplierproducts sp
                    JOIN supplierproductimages spi ON sp.ProductID = spi.ProductID""";
            ResultSet rs = st.executeQuery(query);
            int sizebox = 20;
            while (rs.next()) {
                if (productId == rs.getInt("ProductID")) {
                    String productName = rs.getString("ProductTitle");
                    String des = rs.getString("Description");
                    String coll = rs.getString("Collection");
                    String status = rs.getString("Status");
                    String catagory = rs.getString("Catagory");
                    int productPrice = Integer.parseInt(rs.getString("Price"));
                    int comparePrice = Integer.parseInt(rs.getString("ComparePrice"));
                    String size = rs.getString("Size");
                    String weight = rs.getString("Weight");
                    String color = rs.getString("Color");
                    int delCost = Integer.parseInt(rs.getString("DeliveryCost"));
                    int quantity = Integer.parseInt(rs.getString("Quantity"));

                    Blob img = rs.getBlob("Images");
                    if (img != null) {
                        InputStream is = img.getBinaryStream();
                        byte[] imageBytes = is.readAllBytes();
                        ImageIcon icon = new ImageIcon(imageBytes);
                        Image imgf = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon icon2 = new ImageIcon(imgf);
                        JLabel[] label = new JLabel[] { new JLabel(icon2) };
                        for (JLabel jLabel : label) {
                            jLabel.setBounds(sizebox, 20, 100, 100);
                            sizebox = sizebox + 120;
                            obj.picturePanel.add(jLabel);
                        }
                    }

                    obj.productTitle.setText(productName);
                    obj.descriptionTextArea.setText(des);
                    obj.catalogComboBox.setSelectedItem(catagory);
                    obj.collectionComboBox.setSelectedItem(coll);
                    obj.statusLabel.setText(status);
                    obj.deliveryCost.setText(delCost + "");
                    obj.quantity.setText(quantity + "");
                    obj.size.setText(size);
                    obj.weight.setText(weight);
                    obj.color.setText(color);
                    obj.price.setText(productPrice + "");
                    obj.comparePrice.setText(comparePrice + "");

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        obj.productPanel_ToAdd.revalidate();
        obj.productPanel_ToAdd.setVisible(true);

    }

}
