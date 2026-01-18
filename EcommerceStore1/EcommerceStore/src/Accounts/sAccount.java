package Accounts;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class sAccount {
    public JPanel S_Account;
    JLabel logo;
    JLabel accountNameLabel;
    JLabel storeName;
    JLabel ownerName;
    JLabel accountType;
    JLabel accountID;
    JLabel businessType;
    JLabel CNIC;
    JLabel address;
    JLabel emailAddress;
    JLabel password;
    JTextField storeNameField;
    JTextField ownerNameField;
    JTextField accountTypeField;
    JTextField accountIDField;
    JTextField businessTypeField;
    JTextField CNICField;
    JTextField addressField;
    JTextField emailAddressField;
    JTextField passwordField;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String passwords = "YOUR_MYSQL_PASSWORD_HERE";

    public sAccount(String supplierID) {

        storeNameField = new JTextField();
        storeNameField.setBounds(600, 130, 300, 30);
        storeNameField.setEditable(Boolean.FALSE);

        ownerNameField = new JTextField();
        ownerNameField.setBounds(600, 180, 300, 30);
        ownerNameField.setEditable(Boolean.FALSE);

        accountTypeField = new JTextField();
        accountTypeField.setBounds(600, 230, 300, 30);
        accountTypeField.setEditable(Boolean.FALSE);

        accountIDField = new JTextField();
        accountIDField.setBounds(600, 280, 300, 30);
        accountIDField.setEditable(Boolean.FALSE);

        businessTypeField = new JTextField();
        businessTypeField.setBounds(600, 330, 300, 30);
        businessTypeField.setEditable(Boolean.FALSE);

        CNICField = new JTextField();
        CNICField.setBounds(600, 380, 300, 30);
        CNICField.setEditable(Boolean.FALSE);

        addressField = new JTextField();
        addressField.setBounds(600, 430, 300, 30);
        addressField.setEditable(Boolean.FALSE);

        emailAddressField = new JTextField();
        emailAddressField.setBounds(600, 430, 300, 30);
        emailAddressField.setEditable(Boolean.FALSE);

        passwordField = new JTextField();
        passwordField.setBounds(600, 480, 300, 30);
        passwordField.setEditable(Boolean.FALSE);

        storeName = new JLabel("Store Name");
        storeName.setBounds(150, 130, 100, 30);

        ownerName = new JLabel("Owner Name");
        ownerName.setBounds(150, 180, 100, 30);

        accountType = new JLabel("Account Type");
        accountType.setBounds(150, 230, 100, 30);

        accountID = new JLabel("Account ID");
        accountID.setBounds(150, 280, 100, 30);

        businessType = new JLabel("Business Type");
        businessType.setBounds(150, 330, 100, 30);

        CNIC = new JLabel("CNIC");
        CNIC.setBounds(150, 380, 100, 30);

        address = new JLabel("Address");
        address.setBounds(150, 430, 100, 30);

        emailAddress = new JLabel("Email Address");
        emailAddress.setBounds(150, 430, 100, 30);

        password = new JLabel("Password");
        password.setBounds(150, 480, 100, 30);

        logo = new JLabel();
        logo.setBounds(450, 20, 70, 70);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/Alogo.png"));
        Image image = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(image);
        logo.setIcon(icon2);

        accountNameLabel = new JLabel("Account");
        accountNameLabel.setBounds(450, 100, 70, 30);
        accountNameLabel.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, passwords);
            Statement st = connection.createStatement();

            String query = "select * from supplieraccounts";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (supplierID.equals(rs.getString("SupplierID"))) {
                    storeNameField.setText(rs.getString("StoreName"));
                    ownerNameField.setText(rs.getString("BusinessOwner"));
                    accountTypeField.setText(rs.getString("AccountType"));
                    accountIDField.setText(rs.getString("SupplierID"));
                    businessTypeField.setText(rs.getString("businessType"));
                    CNICField.setText(rs.getString("CNIC"));
                    addressField.setText(rs.getString("Address"));
                    emailAddressField.setText(rs.getString("Email"));
                    passwordField.setText(rs.getString("StorePassword"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        S_Account = new JPanel();
        S_Account.setBounds(200, 50, 1000, 600);
        S_Account.setBackground(Color.WHITE);
        S_Account.setLayout(null);
        S_Account.add(logo);
        S_Account.add(accountNameLabel);
        S_Account.add(storeName);
        S_Account.add(ownerName);
        S_Account.add(accountType);
        S_Account.add(accountID);
        S_Account.add(businessType);
        S_Account.add(CNIC);
        S_Account.add(emailAddress);
        S_Account.add(password);
        S_Account.add(storeNameField);
        S_Account.add(ownerNameField);
        S_Account.add(accountTypeField);
        S_Account.add(accountIDField);
        S_Account.add(businessTypeField);
        S_Account.add(CNICField);
        S_Account.add(emailAddressField);
        S_Account.add(passwordField);
        S_Account.setVisible(true);
    }
}
