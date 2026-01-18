package Accounts;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class cAccount {
    public JPanel C_Account;
    JLabel logo;
    JLabel accountName;
    JLabel fullName;
    JLabel lastName;
    JLabel genderLabel;
    JLabel accountType;
    JLabel birthDate;
    JLabel cEmail;
    JLabel phoneNumber;
    JLabel password;
    JTextField fullNameField;
    JTextField lastNameField;
    JTextField genderField;
    JTextField accountTypeField;
    JTextField birthDateField;
    JTextField emailField;
    JTextField phoneNumberField;
    JPasswordField passwordField;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String cpassword = "YOUR_MYSQL_PASSWORD_HERE";

    public cAccount(String email) {

        fullNameField = new JTextField();
        fullNameField.setBounds(600, 130, 300, 30);

        lastNameField = new JTextField();
        lastNameField.setBounds(600, 180, 300, 30);

        genderField = new JTextField();
        genderField.setBounds(600, 230, 300, 30);

        accountTypeField = new JTextField();
        accountTypeField.setBounds(600, 280, 300, 30);

        birthDateField = new JTextField();
        birthDateField.setBounds(600, 330, 300, 30);

        emailField = new JTextField();
        emailField.setBounds(600, 380, 300, 30);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(600, 430, 300, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(600, 480, 300, 30);

        fullName = new JLabel("Full Name");
        fullName.setBounds(150, 130, 100, 30);

        lastName = new JLabel("Last Name");
        lastName.setBounds(150, 180, 100, 30);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(150, 230, 100, 30);

        accountType = new JLabel("Account Type");
        accountType.setBounds(150, 280, 100, 30);

        birthDate = new JLabel("Birth Date");
        birthDate.setBounds(150, 330, 100, 30);

        cEmail = new JLabel("Email");
        cEmail.setBounds(150, 380, 100, 30);

        phoneNumber = new JLabel("Phone Number");
        phoneNumber.setBounds(150, 430, 100, 30);

        password = new JLabel("Password");
        password.setBounds(150, 480, 100, 30);

        logo = new JLabel();
        logo.setBounds(450, 20, 70, 70);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/cAccount.png"));
        Image img = icon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img);
        logo.setIcon(icon2);

        accountName = new JLabel("Account");
        accountName.setBounds(450, 80, 70, 70);
        accountName.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, cpassword);
            Statement statement = connection.createStatement();

            String query = "select * from signupdetail";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                if (email.equals(resultSet.getString("Email"))) {
                    fullNameField.setText(resultSet.getString("FullName"));
                    lastNameField.setText(resultSet.getString("LastName"));
                    genderField.setText(resultSet.getString("Gender"));
                    accountTypeField.setText(resultSet.getString("AccountType"));
                    birthDateField.setText(resultSet.getString("BirthDate"));
                    emailField.setText(resultSet.getString("Email"));
                    phoneNumberField.setText(resultSet.getString("PhoneNumber"));
                    passwordField.setText(resultSet.getString("Passworrd"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        C_Account = new JPanel();
        C_Account.setBounds(200, 50, 1000, 600);
        C_Account.setBackground(Color.WHITE);
        C_Account.setLayout(null);
        C_Account.add(fullNameField);
        C_Account.add(lastNameField);
        C_Account.add(genderField);
        C_Account.add(accountTypeField);
        C_Account.add(birthDateField);
        C_Account.add(cEmail);
        C_Account.add(phoneNumberField);
        C_Account.add(passwordField);
        C_Account.add(accountName);
        C_Account.add(accountType);
        C_Account.add(birthDate);
        C_Account.add(emailField);
        C_Account.add(phoneNumber);
        C_Account.add(password);
        C_Account.add(logo);
        C_Account.add(accountName);
        C_Account.add(fullName);
        C_Account.add(lastName);
        C_Account.add(genderLabel);
        C_Account.setVisible(true);
    }
}
