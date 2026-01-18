package Project1.src.com.accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Objects;
import java.util.Random;

public class supplierDetails extends JFrame {
    private JTextField storeNameField, firstNameField, lastNameField, supplierID,
            cnic, bankAccount, bankBranch, address, city, zip, email, storePassword, confirmPassword;
    private JComboBox<String> bussinessType, state, bank;
    private JButton submitButton;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public supplierDetails() {
        setTitle("I-Store Supplier Details");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        ImageIcon iconFrame = new ImageIcon(ClassLoader.getSystemResource("images/store2.png"));
        Image iconFrame1 = iconFrame.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon iconFrame1f = new ImageIcon(iconFrame1);
        setIconImage(iconFrame1f.getImage());

        JLabel titleLabel = new JLabel("Supplier Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setForeground(new Color(0x083D77));
        titleLabel.setBounds(350, 15, 300, 40);
        add(titleLabel);

        JLabel subtitleLabel = new JLabel("Please fill the following form with your Business information");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setBounds(300, 65, 400, 20);
        add(subtitleLabel);

        JLabel storeNameLabel = new JLabel("Store Name");
        storeNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        storeNameLabel.setBounds(85, 115, 150, 20);
        add(storeNameLabel);

        storeNameField = new JTextField();
        storeNameField.setBounds(85, 145, 280, 25);
        add(storeNameField);

        JLabel fullNameLabel = new JLabel("Business Owner");
        fullNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        fullNameLabel.setBounds(395, 115, 150, 20);
        add(fullNameLabel);

        firstNameField = new JTextField(); // pre-filled
        firstNameField.setBounds(395, 145, 240, 25);
        add(firstNameField);

        lastNameField = new JTextField(); // pre-filled
        lastNameField.setBounds(665, 145, 240, 25);
        add(lastNameField);

        JLabel supplierIDLabel = new JLabel("Supplier ID");
        supplierIDLabel.setFont(new Font("Arial", Font.BOLD, 14));
        supplierIDLabel.setBounds(85, 203, 100, 20);
        add(supplierIDLabel);

        supplierID = new JTextField(generateSupplierID());
        supplierID.setBounds(85, 233, 240, 25);
        supplierID.setEditable(false);
        add(supplierID);

        JLabel cnicLabel = new JLabel("CNIC");
        cnicLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cnicLabel.setBounds(350, 203, 100, 20);
        add(cnicLabel);

        cnic = new JTextField();
        cnic.setBounds(350, 233, 240, 25);
        add(cnic);

        JLabel businessTypeLabel = new JLabel("Business Type");
        businessTypeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        businessTypeLabel.setBounds(615, 203, 150, 20);
        add(businessTypeLabel);

        bussinessType = new JComboBox<>();
        bussinessType.setBackground(Color.WHITE);
        bussinessType.addItem("Please Select");
        bussinessType.addItem("Tech & Electronics");
        bussinessType.addItem("Fashion & Apparel");
        bussinessType.addItem("Computer & Accessories");
        bussinessType.addItem("Gaming Gear");
        bussinessType.addItem("Smart Home & IOT Product");
        bussinessType.addItem("Audio & Music");
        bussinessType.addItem("Art, Design & Creator Tools");
        bussinessType.addItem("Office & Tech Productivity");
        bussinessType.addItem("Students & Study Essential");
        bussinessType.addItem("Health & Tech Wellness");
        bussinessType.addItem("Other");
        bussinessType.setBounds(615, 233, 290, 25);
        add(bussinessType);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        addressLabel.setBounds(85, 290, 150, 20);
        add(addressLabel);

        address = new JTextField("Street Address");
        address.setBounds(85, 317, 815, 25);
        addFocusListeners(address, "Street Address");
        add(address);

        state = new JComboBox<>();
        state.setBackground(Color.WHITE);
        state.addItem("Please Select");
        state.addItem("Punjab");
        state.addItem("Sindh");
        state.addItem("Balochistan");
        state.addItem("Khyber Pakhtunkhwa");
        state.setBounds(85, 360, 300, 25);
        add(state);

        city = new JTextField("City");
        city.setBounds(409, 360, 235, 25);
        addFocusListeners(city, "City");
        add(city);

        zip = new JTextField("Zip Code");
        zip.setBounds(662, 360, 239, 25);
        addFocusListeners(zip, "Zip Code");
        add(zip);

        JLabel bankingLabel = new JLabel("Banking & Payment Details");
        bankingLabel.setFont(new Font("Arial", Font.BOLD, 14));
        bankingLabel.setBounds(85, 420, 200, 20);
        add(bankingLabel);

        bank = new JComboBox<>();
        bank.setBackground(Color.WHITE);
        bank.addItem("Please Select");
        bank.addItem("National Bank of Pakistan (NBP)");
        bank.addItem("Habib Bank Limited (HBL)");
        bank.addItem("United Bank Limited (UBL)");
        bank.addItem("MCB Bank Limited (MCB)");
        bank.addItem("Allied Bank Limited");
        bank.addItem("Meezan Bank Limited");
        bank.addItem("Askari Bank");
        bank.setBounds(85, 450, 300, 25);
        add(bank);

        bankBranch = new JTextField("Branch");
        bankBranch.setBounds(409, 450, 235, 25);
        addFocusListeners(bankBranch, "Branch");
        add(bankBranch);

        bankAccount = new JTextField("Account Number");
        bankAccount.setBounds(662, 450, 239, 25);
        addFocusListeners(bankAccount, "Account Number");
        add(bankAccount);

        email = new JTextField("Email");
        email.setBounds(85, 520, 300, 25);
        add(email);

        storePassword = new JTextField("Password");
        storePassword.setBounds(409, 520, 235, 25);
        add(storePassword);

        confirmPassword = new JTextField("Confirm Password");
        confirmPassword.setBounds(662, 520, 239, 25);
        add(confirmPassword);

        submitButton = new JButton("Submit");
        submitButton.setBounds(340, 570, 300, 30);
        submitButton.setBackground(new Color(0x083D77));
        submitButton.setForeground(Color.WHITE);
        add(submitButton);

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Validate all required fields
                if (storeNameField.getText().isEmpty() ||
                        firstNameField.getText().isEmpty() ||
                        lastNameField.getText().isEmpty() ||
                        cnic.getText().isEmpty() ||
                        bussinessType.getSelectedIndex() == 0 ||
                        address.getText().equals("Street Address") || address.getText().isEmpty() ||
                        state.getSelectedIndex() == 0 ||
                        city.getText().equals("City") || city.getText().isEmpty() ||
                        bankAccount.getText().equals("Zip Code") || bankAccount.getText().isEmpty() ||
                        bankBranch.getText().equals("Branch") || bankBranch.getText().isEmpty() ||
                        zip.getText().equals("Account Number") || zip.getText().isEmpty() ||
                        bank.getSelectedIndex() == 0 || email.getText().isEmpty() || storePassword.getText().isEmpty()
                        || confirmPassword.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Validation Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement();

                    String cSupplierID = supplierID.getText();
                    String owner = firstNameField.getText() + " " + lastNameField.getText();
                    String cAddress = address.getText() + city.getText() + zip.getText();
                    String cStorePassword = storePassword.getText();
                    String cConfirmPassword = confirmPassword.getText();
                    String accountType = "Supplier";
                    String query = String.format(
                            "INSERT INTO supplieraccounts(StoreName, BusinessOwner, SupplierID, CNIC, BusinessType, Address, Email, StorePassword, ConfirmPassword, AccountType) Values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            storeNameField.getText(), owner, cSupplierID, cnic.getText(),
                            Objects.requireNonNull(bussinessType.getSelectedItem()), cAddress, email.getText(),
                            cStorePassword, cConfirmPassword, accountType);
                    int affectedrows = statement.executeUpdate(query);

                    if (affectedrows > 0) {
                        String add = String.format(
                                "INSERT INTO accounts(Username, Email, Password, AccountType, SupplierID) Values ('%s', '%s', '%s', '%s','%s')",
                                firstNameField.getText(), email.getText(), cStorePassword, accountType, cSupplierID);
                        statement.executeUpdate(add);
                    }
                    try {
                        String bankQuery = String.format(
                                "INSERT INTO bankaccount(Bank, Branch, AccountNumber, SupplierID) Values ('%s', '%s', '%s', '%s')",
                                bank.getSelectedItem(), bankBranch.getText(), bankAccount.getText(), cSupplierID);
                        statement.executeUpdate(bankQuery);
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                // CNIC Validation: Must be exactly 13 digits
                String cnicText = cnic.getText().trim();
                if (!cnicText.matches("\\d{13}")) {
                    JOptionPane.showMessageDialog(null, "CNIC must be exactly 13 numeric digits.", "Invalid CNIC",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Zip Code Validation (entered in bankAccount): Must be numeric
                String zipText = zip.getText().trim();
                if (!zipText.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Zip Code must be numeric.", "Invalid Zip Code",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If all validations pass
                String message = "Submitted:\n" +
                        "Supplier ID: " + supplierID.getText() + "\n" +
                        "Business Owner: " + firstNameField.getText() + " " + lastNameField.getText() + "\n" +
                        "Store Name: " + storeNameField.getText();
                JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });

        setVisible(true);
    }

    private void addFocusListeners(JTextField field, String placeholder) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private String generateSupplierID() {
        Random rand = new Random();
        return "SUP" + (100000 + rand.nextInt(900000)); // e.g., SUP723498
    }

    public static void main(String[] args) {
        new supplierDetails(); // sample test
    }
}
