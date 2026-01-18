package sDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import Project1.src.com.accounts.supplierDetails;

public class addProductPanel {

    JScrollPane productPanel_ToAdd;
    JLabel headingLabel;
    JPanel scrollContentPanel;
    JPanel productTextPanel;
    JPanel picturePanel;
    JTextField productTitle;
    JTextArea descriptionTextArea;
    JLabel productDescriptionLabel;
    JLabel productTitleLabel;
    JLabel mediaLabel;
    JButton addProductPicButton;
    JPanel catalogPanel;
    JComboBox catalogComboBox;
    JComboBox collectionComboBox;
    JLabel pricelabel;
    JTextField price;
    JLabel comparePriceLabel;
    JTextField comparePrice;
    JLabel sizeLabel;
    JTextField size;
    JLabel weightLabel;
    JTextField weight;
    JLabel colorLabel;
    JTextField color;
    JLabel deliveryCostLabel;
    JTextField deliveryCost;
    JLabel quantityLabel;
    JTextField quantity;
    JLabel statusLabel;
    JRadioButton activeRadioButton;
    JRadioButton draftRadioButton;
    JButton saveButton;
    File[] files;
    String supplierID;
    int productID;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public addProductPanel(String supplierID) {
        this.supplierID = supplierID;
        components();
    }

    // ----Button to save Product-----
    public void components() {
        saveButton = new JButton("Add Product");
        saveButton.setFocusable(Boolean.FALSE);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(Color.BLACK);
        saveButton.setFocusPainted(false);
        saveButton.setBounds(350, 320, 150, 30);

        // ----Add product Button Action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = productTitle.getText();
                String desc = descriptionTextArea.getText();
                String priceText = price.getText();
                try {
                    if (name.isEmpty() || desc.isEmpty() || priceText.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid price.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println(e1.getMessage());
                }
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    String query = String.format(
                            "INSERT INTO supplierproducts (ProductTitle, Description, Catagory, Collection, Price, ComparePrice, Size, Weight, Color,DeliveryCost, Status, SupplierID, Quantity) VALUES ('%s','%s','%s','%s','%d','%d','%s','%s','%s','%d','%s','%s','%d')",
                            productTitle.getText(), descriptionTextArea.getText(),
                            catalogComboBox.getSelectedItem().toString(),
                            collectionComboBox.getSelectedItem().toString(), Integer.parseInt(price.getText()),
                            Integer.parseInt(comparePrice.getText()),
                            size.getText(), weight.getText(), color.getText(), Integer.parseInt(deliveryCost.getText()),
                            statusLabel.getText(), supplierID, Integer.parseInt(quantity.getText()));
                    PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                    int affectedRows = pst.executeUpdate();
                    if (affectedRows > 0) {
                        ResultSet generatedKeys = pst.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int productId = generatedKeys.getInt(1);

                            if (files != null) {
                                String imgQuery = "INSERT INTO supplierproductimages (ProductID, Images,SupplierID) VALUES (?, ?,?)";
                                PreparedStatement imgPst = connection.prepareStatement(imgQuery);

                                for (File file : files) {
                                    FileInputStream fis = new FileInputStream(file);
                                    imgPst.setInt(1, productId);
                                    imgPst.setBinaryStream(2, fis, (int) file.length());
                                    imgPst.setString(3, supplierID);
                                    imgPst.executeUpdate();
                                    fis.close();
                                }
                            }

                            JOptionPane.showMessageDialog(null, "Product saved successfully!");
                        }
                    }

                } catch (SQLException | IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // ---Radio Buttons----
        draftRadioButton = new JRadioButton("Draft");
        draftRadioButton.setFocusable(false);
        draftRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        draftRadioButton.setBounds(200, 260, 70, 30);

        activeRadioButton = new JRadioButton("Active");
        activeRadioButton.setBounds(100, 260, 70, 30);
        activeRadioButton.setFocusable(Boolean.FALSE);
        activeRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 12));

        ButtonGroup group = new ButtonGroup();
        group.add(activeRadioButton);
        group.add(draftRadioButton);

        // ---Check Status of Product---
        statusLabel = new JLabel("Status");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBackground(Color.white);
        statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        statusLabel.setBounds(20, 260, 50, 30);

        // ---- Quantity
        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(440, 200, 80, 30);
        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");
        minusButton.setBounds(535, 200, 50, 30);
        minusButton.setFocusable(false);
        minusButton.setBackground(new Color(0xFAF9F6));
        quantity = new JTextField();
        quantity.setBounds(580, 200, 80, 30);
        quantity.setBackground(new Color(0xFAF9F6));
        plusButton.setBounds(660, 200, 50, 30);
        plusButton.setFocusable(false);
        plusButton.setBackground(new Color(0xFAF9F6));

        minusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int text = Integer.parseInt(quantity.getText());
                if (text > 0) {
                    text--;
                    quantity.setText(String.valueOf(text));
                } else {
                    quantity.setText("0");
                }
            }
        });

        plusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int text = Integer.parseInt(quantity.getText());
                if (text > 0) {
                    text++;
                    quantity.setText(String.valueOf(text));
                } else {
                    quantity.setText("0");
                }
            }
        });

        // ----Enter The Delivery Cost Code---
        deliveryCost = new JTextField();
        deliveryCost.setForeground(Color.BLACK);
        deliveryCost.setBounds(200, 200, 200, 30);
        deliveryCost.setBackground(new Color(0xFAF9F6));

        deliveryCostLabel = new JLabel("Delivery Cost");
        deliveryCostLabel.setForeground(Color.BLACK);
        deliveryCostLabel.setBackground(Color.WHITE);
        deliveryCostLabel.setBounds(30, 200, 100, 30);
        deliveryCostLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // ----Size Weight And Color of the Product---
        color = new JTextField();
        color.setBounds(510, 140, 100, 30);
        color.setForeground(Color.BLACK);
        color.setBackground(new Color(0xFAF9F6));

        colorLabel = new JLabel("Color");
        colorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        colorLabel.setForeground(Color.BLACK);
        colorLabel.setBackground(Color.WHITE);
        colorLabel.setBounds(420, 140, 50, 30);

        weight = new JTextField();
        weight.setBounds(300, 140, 100, 30);
        weight.setBackground(new Color(0xFAF9F6));
        weight.setForeground(Color.BLACK);

        weightLabel = new JLabel();
        weightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weightLabel.setText("Weight");
        weightLabel.setBounds(200, 140, 50, 30);
        weightLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

        size = new JTextField();
        size.setBackground(new Color(0xFAF9F6));
        size.setForeground(Color.BLACK);
        size.setBounds(80, 140, 100, 30);

        sizeLabel = new JLabel();
        sizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sizeLabel.setText("Size");
        sizeLabel.setBounds(30, 140, 30, 30);
        sizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

        // ---Price and Compare Price Code---
        comparePrice = new JTextField();
        comparePrice.setBounds(500, 80, 250, 30);
        comparePrice.setForeground(Color.BLACK);
        comparePrice.setBackground(new Color(0xFAF9F6));

        comparePriceLabel = new JLabel("Compare Price");
        comparePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        comparePriceLabel.setBounds(380, 80, 100, 30);
        comparePriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        comparePriceLabel.setBackground(Color.WHITE);
        comparePriceLabel.setForeground(Color.BLACK);

        price = new JTextField();
        price.setBounds(100, 80, 250, 30);
        price.setBackground(new Color(0xFAF9F6));
        price.setForeground(Color.BLACK);

        pricelabel = new JLabel();
        pricelabel.setHorizontalAlignment(SwingConstants.CENTER);
        pricelabel.setText("Price");
        pricelabel.setVerticalAlignment(SwingConstants.CENTER);
        pricelabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        pricelabel.setForeground(Color.BLACK);
        pricelabel.setBounds(20, 80, 50, 30);

        // ---Collection and Product Catagory---
        collectionComboBox = new JComboBox();
        collectionComboBox.setFocusable(false);
        collectionComboBox.setBackground(new Color(0xFAF9F6));
        collectionComboBox.setForeground(Color.BLACK);
        collectionComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        collectionComboBox.setBounds(440, 20, 400, 30);
        collectionComboBox.addItem("Collection");
        collectionComboBox.addItem("Men");
        collectionComboBox.addItem("Women");

        catalogComboBox = new JComboBox();
        catalogComboBox.setBounds(20, 20, 400, 30);
        catalogComboBox.setBackground(new Color(0xFAF9F6));
        catalogComboBox.setForeground(Color.BLACK);
        catalogComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
        catalogComboBox.addItem("Select");
        catalogComboBox.addItem("Cloths");
        catalogComboBox.addItem("Shoes");
        catalogComboBox.addItem("Watches");
        catalogComboBox.addItem("Electronics");
        catalogComboBox.addItem("Mobile Accessories");
        catalogComboBox.addItem("Home Decor");
        catalogComboBox.addItem("Beauty & Personal Care");
        catalogComboBox.addItem("Toys & Games");
        catalogComboBox.addItem("Books & Stationery");
        catalogComboBox.addItem("Groceries");
        catalogComboBox.addItem("Fitness Equipment");
        catalogComboBox.addItem("Kitchen Appliances");
        catalogComboBox.addItem("Jewelry");
        catalogComboBox.addItem("Bags & Luggage");
        catalogComboBox.addItem("Automobile Accessories");
        catalogComboBox.addItem("Pet Supplies");
        catalogComboBox.addItem("Office Supplies");

        // ---Panel To add Product Qualities---
        catalogPanel = new JPanel(null);
        catalogPanel.setPreferredSize(new Dimension(860, 400));
        catalogPanel.setBackground(Color.WHITE);
        catalogPanel.add(catalogComboBox);
        catalogPanel.add(collectionComboBox);
        catalogPanel.add(pricelabel);
        catalogPanel.add(price);
        catalogPanel.add(comparePriceLabel);
        catalogPanel.add(comparePrice);
        catalogPanel.add(sizeLabel);
        catalogPanel.add(size);
        catalogPanel.add(weightLabel);
        catalogPanel.add(weight);
        catalogPanel.add(colorLabel);
        catalogPanel.add(color);
        catalogPanel.add(saveButton);
        catalogPanel.add(statusLabel);
        catalogPanel.add(activeRadioButton);
        catalogPanel.add(draftRadioButton);
        catalogPanel.add(deliveryCostLabel);
        catalogPanel.add(deliveryCost);
        catalogPanel.add(quantityLabel);
        catalogPanel.add(quantity);
        catalogPanel.add(minusButton);
        catalogPanel.add(plusButton);

        // ----Panel To add product Text---
        productTextPanel = new JPanel();
        productTextPanel.setLayout(null);
        productTextPanel.setPreferredSize(new Dimension(920, 450));
        productTextPanel.setBackground(Color.WHITE);

        // ---Title
        productTitleLabel = new JLabel("Title");
        productTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        productTitleLabel.setForeground(Color.BLACK);
        productTitleLabel.setBounds(20, 20, 50, 30);

        productTitle = new JTextField();
        productTitle.setLayout(null);
        productTitle.setBounds(20, 55, 860, 40);
        productTitle.setBackground(new Color(0xFAF9F6));
        productTitle.setForeground(Color.BLACK);
        productTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));

        // ---Description
        productDescriptionLabel = new JLabel("Description");
        productDescriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        productDescriptionLabel.setForeground(Color.BLACK);
        productDescriptionLabel.setBounds(20, 120, 100, 30);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setBounds(20, 160, 860, 250);
        descriptionTextArea.setBackground(new Color(0xFAF9F6));
        descriptionTextArea.setForeground(Color.BLACK);
        descriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionTextArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        descriptionTextArea.setLineWrap(true);

        productTextPanel.add(productTitleLabel);
        productTextPanel.add(productTitle);
        productTextPanel.add(productDescriptionLabel);
        productTextPanel.add(descriptionTextArea);

        // ---Label To highhlight Media
        mediaLabel = new JLabel("Media");
        mediaLabel.setBounds(20, 10, 100, 30);
        mediaLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        mediaLabel.setForeground(Color.BLACK);

        // ----Add product Images Panel-----
        addProductPicButton = new JButton("Import");
        addProductPicButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        addProductPicButton.setBackground(new Color(0xFAF9F6));
        addProductPicButton.setForeground(Color.BLACK);
        addProductPicButton.setBounds(750, 10, 100, 30);

        // ---Action To add Images---
        addProductPicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    files = fileChooser.getSelectedFiles();
                    int sizebox = 20;
                    for (File file : files) {
                        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        ImageIcon icon2 = new ImageIcon(img);
                        JLabel[] label = new JLabel[] { new JLabel(icon2) };
                        for (JLabel jLabel : label) {
                            jLabel.setBounds(sizebox, 20, 100, 100);
                            sizebox = sizebox + 120;
                            picturePanel.add(jLabel);
                        }
                    }
                    picturePanel.setVisible(true);
                }
                picturePanel.revalidate();
                picturePanel.repaint();
            }
        });

        // ---Panel For Pictures---
        picturePanel = new JPanel();
        picturePanel.setBounds(20, 50, 860, 250);
        picturePanel.setLayout(null);
        picturePanel.setBackground(new Color(0xFAF9F6));
        picturePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ---Multi Panels---
        JPanel mediaPanel = new JPanel(null);
        mediaPanel.setPreferredSize(new Dimension(920, 320));
        mediaPanel.setBackground(Color.WHITE);
        mediaPanel.add(mediaLabel);
        mediaPanel.add(addProductPicButton);
        mediaPanel.add(picturePanel);

        // ----Heading----
        headingLabel = new JLabel("Add Product");
        headingLabel.setBounds(20, 20, 150, 80);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("images/boxproduct.png"))
                .getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH)));

        // ----Add Product Page Panels----
        scrollContentPanel = new JPanel();
        scrollContentPanel.setLayout(new BoxLayout(scrollContentPanel, BoxLayout.Y_AXIS));
        scrollContentPanel.setBackground(new Color(0xFAF9F6));
        scrollContentPanel.add(headingLabel);
        scrollContentPanel.add(productTextPanel);
        scrollContentPanel.add(Box.createVerticalStrut(20));
        scrollContentPanel.add(mediaPanel);
        scrollContentPanel.add(Box.createVerticalStrut(20));
        scrollContentPanel.add(catalogPanel);
        scrollContentPanel.add(Box.createVerticalStrut(20));

        productPanel_ToAdd = new JScrollPane(scrollContentPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        productPanel_ToAdd.setBounds(200, 50, 1000, 600);
        productPanel_ToAdd.getVerticalScrollBar().setUnitIncrement(20);
    }
}
