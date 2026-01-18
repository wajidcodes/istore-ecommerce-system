package Project1.src.com.accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.util.Arrays;
import java.util.Objects;
import cDashboard.*;
import sDashboard.*;
import Admin.admin;

//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.services.oauth2.Oauth2;
//import com.google.api.services.oauth2.model.Userinfo;

public class LoginForm extends JFrame {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password1 = "YOUR_MYSQL_PASSWORD_HERE";
    JButton loginButton;
    JTextField emailField;
    JPasswordField passwordField;

    public LoginForm() {

        setTitle("IStore");
        setSize(1000, 650);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        ImageIcon iconFrame = new ImageIcon(ClassLoader.getSystemResource("images/store2.png"));
        Image iconFrame1 = iconFrame.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon iconFrame1f = new ImageIcon(iconFrame1);
        setIconImage(iconFrame1f.getImage());
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0, 0, 500, 650);
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(0xef9505));
        loginPanel.setBounds(500, 0, 500, 650);
        add(loginPanel);

        JLabel sideImage = new JLabel();
        sideImage.setBounds(0, 0, 500, 650);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/realsidelogin.png"));
        Image img1 = img.getImage().getScaledInstance(500, 650, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img1);
        sideImage.setIcon(img2);
        loginPanel.add(sideImage);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setBounds(200, 70, 250, 50);
        titleLabel.setForeground(new Color(0x083D77));
        mainPanel.add(titleLabel);

        emailField = new JTextField("Email address");
        emailField.setBounds(100, 150, 300, 40);
        emailField.setForeground(Color.GRAY);
        emailField.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        emailField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Email address")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setText("Email address");
                    emailField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(emailField);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 200, 300, 40);
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Time New Roman", Font.PLAIN, 16));
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•');
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setText("Password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        mainPanel.add(passwordField);

        JButton forgotPassBtn = new JButton("Forgot password?");
        forgotPassBtn.setBounds(190, 240, 300, 40);
        forgotPassBtn.setBorderPainted(false);
        forgotPassBtn.setContentAreaFilled(false);
        mainPanel.add(forgotPassBtn);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 300, 250, 40);
        loginButton.setBackground(new Color(0x083D77));
        loginButton.setForeground(Color.WHITE);
        mainPanel.add(loginButton);

        loginButton.addActionListener(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e1) {
                System.out.println(e1.getMessage());
            }
            String inputEmail = emailField.getText().trim();
            String inputPassword = new String(passwordField.getPassword()); // convert password char[] to String
                                                                            // correctly

            if (inputEmail.equals("Email address") || inputPassword.equals("Password") || inputEmail.isEmpty()
                    || inputPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Email and Password");
                return;
            }

            try (Connection connection = DriverManager.getConnection(url, user, password1);
                    PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts")) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    String email = rs.getString("Email");
                    String dbPassword = rs.getString("Password");
                    String accountType = rs.getString("AccountType");

                    if (inputEmail.equals(email) && inputPassword.equals(dbPassword)) {

                        if (accountType.equalsIgnoreCase("Customer")) {
                            new C_Dashboard(email);
                            dispose();
                        } else if (accountType.equalsIgnoreCase("Supplier")) {
                            String supplierID = rs.getString("SupplierID");
                            if (supplierID != null && !supplierID.isEmpty()) {
                                new S_Dashboard(supplierID);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "SupplierID not found!");
                            }
                        } else if (accountType.equalsIgnoreCase("Admin")) {
                            new admin();
                            dispose();
                        }
                    }
                }

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        JLabel orLabel = new JLabel("-------- Or Login with --------");
        orLabel.setBounds(170, 360, 150, 20);
        mainPanel.add(orLabel);

        JButton googleButton = new JButton("Google");
        googleButton.setBounds(115, 390, 120, 40);
        googleButton.setBackground(new Color(0x083D77));
        googleButton.setForeground(Color.WHITE);
        googleButton.addActionListener(e -> {
            // try {
            // final NetHttpTransport httpTransport =
            // GoogleNetHttpTransport.newTrustedTransport();
            // final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            //
            // GoogleClientSecrets clientSecrets = new GoogleClientSecrets()
            // .setInstalled(new GoogleClientSecrets.Details()
            // .setClientId("YOUR_GOOGLE_CLIENT_ID")
            // .setClientSecret("YOUR_GOOGLE_CLIENT_SECRET"));
            //
            // GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            // httpTransport, jsonFactory, clientSecrets,
            // java.util.List.of("https://www.googleapis.com/auth/userinfo.profile",
            // "https://www.googleapis.com/auth/userinfo.email"))
            // .setAccessType("offline")
            // .build();
            //
            // LocalServerReceiver receiver = new
            // LocalServerReceiver.Builder().setPort(8888).build();
            // Credential credential = new AuthorizationCodeInstalledApp(flow,
            // receiver).authorize("user");
            //
            // Oauth2 oauth2 = new Oauth2.Builder(httpTransport, jsonFactory, credential)
            // .setApplicationName("I Store")
            // .build();
            //
            // Userinfo userInfo = oauth2.userinfo().get().execute();
            //
            // JOptionPane.showMessageDialog(this,
            // "Welcome, " + userInfo.getName() + "\nEmail: " + userInfo.getEmail());
            //
            // System.out.println("Name: " + userInfo.getName());
            // System.out.println("Email: " + userInfo.getEmail());
            // System.out.println("Picture URL: " + userInfo.getPicture());
            //
            // } catch (Exception ex) {
            // ex.printStackTrace();
            // JOptionPane.showMessageDialog(this, "Google Login Failed: " +
            // ex.getMessage());

        });
        mainPanel.add(googleButton);

        JButton fbButton = new JButton("Facebook");
        fbButton.setBounds(260, 390, 120, 40);
        fbButton.setBackground(new Color(0x083D77));
        fbButton.setForeground(Color.WHITE);
        mainPanel.add(fbButton);

        JButton signupBtn = new JButton("Don't have an account, Signup");
        signupBtn.setBounds(120, 440, 250, 30);
        signupBtn.setFont(new Font("Serif", Font.PLAIN, 16));
        signupBtn.setBorderPainted(false);
        signupBtn.setContentAreaFilled(false);
        mainPanel.add(signupBtn);

        forgotPassBtn.addActionListener(e -> new ForgotPasswordForm());
        signupBtn.addActionListener(e -> new SignupForm());

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
