package Project1.src.com.accounts;

import com.mysql.cj.x.protobuf.MysqlxCrud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.regex.Pattern;

public class SignupForm extends JFrame {
    private JTextField firstNameField, lastNameField, emailField, phoneField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> genderComboBox, monthComboBox, dayComboBox, yearComboBox;
    private JRadioButton supplier, customer;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public SignupForm() {
        setTitle("I-Store Registration");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        ImageIcon iconFrame = new ImageIcon(ClassLoader.getSystemResource("images/store2.png"));
        Image iconFrame1 = iconFrame.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon iconFrame1f = new ImageIcon(iconFrame1);
        setIconImage(iconFrame1f.getImage());
        setResizable(false);

        // Title
        JLabel titleLabel = new JLabel("Signup Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setBounds(350, 15, 300, 40);
        titleLabel.setForeground(new Color(0x083D77));
        add(titleLabel);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Please fill the following form with your personal information");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setBounds(300, 65, 400, 20);
        add(subtitleLabel);

        // Full Name Label
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fullNameLabel.setBounds(50, 100, 150, 20);
        add(fullNameLabel);

        // First Name
        firstNameField = new JTextField();
        firstNameField.setBounds(50, 130, 180, 25);
        add(firstNameField);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(50, 153, 80, 20);
        add(firstNameLabel);

        // Last Name
        lastNameField = new JTextField();
        lastNameField.setBounds(250, 130, 180, 25);
        add(lastNameField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(250, 153, 80, 20);
        add(lastNameLabel);

        // Gender Label
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genderLabel.setBounds(450, 100, 100, 20);
        add(genderLabel);

        // Gender Dropdown
        genderComboBox = new JComboBox<>();
        genderComboBox.setBackground(Color.WHITE);
        genderComboBox.addItem("Please Select");
        genderComboBox.addItem("Male");
        genderComboBox.addItem("Female");
        genderComboBox.addItem("Other");
        genderComboBox.setBounds(450, 130, 255, 25);
        add(genderComboBox);

        JLabel accTypeLabel = new JLabel("Account Type");
        accTypeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        accTypeLabel.setBounds(730, 100, 100, 20);
        add(accTypeLabel);

        customer = new JRadioButton("Customer");
        customer.setBounds(725, 130, 100, 25);
        customer.setFont(new Font("Arial", Font.PLAIN, 14));
        customer.setBackground(Color.WHITE);
        add(customer);

        supplier = new JRadioButton("Supplier");
        supplier.setBounds(825, 130, 100, 25);
        supplier.setBackground(Color.WHITE);
        supplier.setFont(new Font("Arial", Font.PLAIN, 14));
        add(supplier);
        supplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (supplier.isSelected()) {
                    new supplierDetails(); // open supplierDetails window
                    dispose(); // close current window
                } else {
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    dispose();
                }
            }
        });

        // Group the buttons
        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(customer);
        accountTypeGroup.add(supplier);

        // Birth Date Label
        JLabel birthDateLabel = new JLabel("Birth Date");
        birthDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        birthDateLabel.setBounds(50, 203, 100, 20);
        add(birthDateLabel);

        // Day Dropdown
        dayComboBox = new JComboBox<>();
        dayComboBox.setBackground(Color.WHITE);
        dayComboBox.addItem("Please select a day");
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.valueOf(i));
        }
        dayComboBox.setBounds(50, 233, 279, 25);
        add(dayComboBox);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setBounds(50, 256, 50, 20);
        add(dayLabel);

        // Month Dropdown
        monthComboBox = new JComboBox<>();
        monthComboBox.setBackground(Color.WHITE);
        monthComboBox.addItem("Please select a month");
        String[] months = new java.text.DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length - 1; i++) {
            monthComboBox.addItem(months[i]);
        }
        monthComboBox.setBounds(347, 233, 279, 25);
        add(monthComboBox);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setBounds(347, 256, 50, 20);
        add(monthLabel);

        // Year Dropdown
        yearComboBox = new JComboBox<>();
        yearComboBox.setBackground(Color.WHITE);
        yearComboBox.addItem("Please select a year");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 100; i--) {
            yearComboBox.addItem(String.valueOf(i));
        }
        yearComboBox.setBounds(646, 233, 279, 25);
        add(yearComboBox);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setBounds(646, 256, 50, 20);
        add(yearLabel);

        // Email
        JLabel emailLabel = new JLabel("E-mail");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setBounds(50, 310, 100, 20);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(50, 340, 425, 25);
        add(emailField);

        JLabel emailExample = new JLabel("ex: myname@example.com");
        emailExample.setFont(new Font("Arial", Font.PLAIN, 12));
        emailExample.setForeground(Color.GRAY);
        emailExample.setBounds(50, 365, 200, 20);
        add(emailExample);

        // Phone
        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        phoneLabel.setBounds(505, 310, 120, 20);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(505, 340, 425, 25);
        add(phoneField);

        JLabel phoneFormat = new JLabel("0300-0000000");
        phoneFormat.setFont(new Font("Arial", Font.PLAIN, 12));
        phoneFormat.setForeground(Color.GRAY);
        phoneFormat.setBounds(505, 365, 100, 20);
        add(phoneFormat);

        JLabel phoneValidation = new JLabel("Please enter a valid phone number.");
        phoneValidation.setFont(new Font("Arial", Font.PLAIN, 12));
        phoneValidation.setForeground(Color.GRAY);
        phoneValidation.setBounds(605, 365, 200, 20);
        add(phoneValidation);

        // Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setBounds(50, 419, 100, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 449, 425, 25);
        add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        confirmPasswordLabel.setBounds(505, 419, 150, 20);
        add(confirmPasswordLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(505, 449, 425, 25);
        add(confirmPasswordField);

        JLabel passwordRequirements = new JLabel(
                "Password must contain:     8+ characters, 1 uppercase, 1 lowercase, 1 number, 1 special character");
        passwordRequirements.setFont(new Font("Arial", Font.PLAIN, 10));
        passwordRequirements.setForeground(Color.GRAY);
        passwordRequirements.setBounds(250, 479, 600, 20);
        add(passwordRequirements);

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 540, 300, 30);
        submitButton.setBackground(new Color(0x083D77));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this::validateForm);
        add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println(e1.getMessage());
                }
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement();

                    String year = (String) yearComboBox.getSelectedItem();
                    int month = monthComboBox.getSelectedIndex();
                    String day = (String) dayComboBox.getSelectedItem();
                    String password = new String(passwordField.getPassword());
                    String confirmPassword = new String(confirmPasswordField.getPassword());

                    String birthDateString = year + "-" + String.format("%02d", month) + "-"
                            + String.format("%02d", Integer.parseInt(day));
                    String accountType = "Customer";

                    String query = String.format(
                            "INSERT INTO signupdetail(FullName, LastName, Gender, AccountType, BirthDate, Email, PhoneNumber, Passworrd, ConfirmPassword) Values ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
                            firstNameField.getText(), lastNameField.getText(), genderComboBox.getSelectedItem(),
                            accountType, birthDateString, emailField.getText(), phoneField.getText(), password,
                            confirmPassword);
                    int rowsaffected = statement.executeUpdate(query);
                    if (rowsaffected > 0) {
                        // JOptionPane.showMessageDialog(null, "Signup Successful");
                        String add = String.format(
                                "INSERT INTO accounts(Username, Email, Password, AccountType) Values ('%s', '%s', '%s', '%s')",
                                firstNameField.getText(), emailField.getText(), password, accountType);
                        statement.executeUpdate(add);
                        JOptionPane.showMessageDialog(null, "Account Added Successfully");
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Signup Failed");
                    }
                    connection.close();

                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }

            }
        });

        setVisible(true);
    }

    private void validateForm(ActionEvent e) {
        // Password validation
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(this,
                    "Password must contain:\n" +
                            "- 8+ characters\n" +
                            "- 1 uppercase letter\n" +
                            "- 1 lowercase letter\n" +
                            "- 1 number\n" +
                            "- 1 special character");
            return;
        }

        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() ||
                emailField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                genderComboBox.getSelectedIndex() == 0 || monthComboBox.getSelectedIndex() == 0 ||
                dayComboBox.getSelectedIndex() == 0 || yearComboBox.getSelectedIndex() == 0 ||
                (!customer.isSelected() && !supplier.isSelected())) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields!");
            return;
        }

        // Handle account type redirection
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public static void main(String[] args) {
        new SignupForm();
    }
}
