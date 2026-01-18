package Project1.src.com.accounts;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.security.MessageDigest;

public class ForgotPasswordForm extends JFrame {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public ForgotPasswordForm() {
        setTitle("Forgot Password - I Store");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel title = new JLabel("Reset Your Password");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(90, 30, 300, 30);
        add(title);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 90, 80, 20);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(180, 90, 200, 25);
        add(emailField);

        JLabel passLabel = new JLabel("New Password:");
        passLabel.setBounds(50, 130, 120, 20);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(180, 130, 200, 25);
        add(passField);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        confirmLabel.setBounds(50, 170, 120, 20);
        add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(180, 170, 200, 25);
        add(confirmField);

        JButton resetBtn = new JButton("Reset Password");
        resetBtn.setBounds(180, 220, 150, 30);
        add(resetBtn);

        resetBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String newPassword = new String(passField.getPassword());
            String confirmPassword = new String(confirmField.getPassword());

            if (email.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }

            try {
                Connection conn = DriverManager.getConnection(url, user, password);

                PreparedStatement checkStmt = conn.prepareStatement(
                        "SELECT * FROM accounts WHERE Email = ?");
                checkStmt.setString(1, email);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    PreparedStatement updateStmt = conn.prepareStatement(
                            "UPDATE accounts SET Password = ? WHERE Email = ?");
                    updateStmt.setString(1, newPassword); // You can hash this password
                    updateStmt.setString(2, email);
                    updateStmt.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Password updated successfully!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Email not found.");
                }

                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
