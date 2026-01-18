package HelpCenter;

import cDashboard.C_Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Help {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/admin";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public JPanel panel;

    public Help() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(200, 50, 1000, 600);

        JLabel label = new JLabel("Help Center");
        label.setBounds(400, 50, 200, 50);
        label.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(label);

        JLabel label_1 = new JLabel("Type");
        label_1.setBounds(200, 120, 50, 30);
        panel.add(label_1);

        JTextField textField = new JTextField();
        textField.setBounds(200, 160, 500, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(textField);

        JLabel label_2 = new JLabel("Customer/Supplier");
        label_2.setBounds(650, 120, 150, 30);
        panel.add(label_2);

        JTextField textField_1 = new JTextField();
        textField_1.setBounds(710, 160, 90, 30);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(textField_1);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(200, 200, 600, 280);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel.add(textArea);

        JButton button = new JButton("Send");
        button.setBounds(700, 500, 80, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e1) {
                    System.out.println(e1.getMessage());
                }
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);

                    String query = "INSERT INTO applications (`Title`, `Description`, `From`) VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, textField.getText());
                    preparedStatement.setString(2, textArea.getText());
                    preparedStatement.setString(3, textField_1.getText());
                    int result = preparedStatement.executeUpdate();

                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Successfully sent to the application");
                    }

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel.setVisible(true);

    }

}
