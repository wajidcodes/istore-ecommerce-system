package cDashboard;

import java.sql.*;

public class sqlPractice {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            String query = "select * from supplieraccounts";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String id = resultSet.getString("username");
                String password = resultSet.getString("password");
                System.out.println("Username " + id);
                System.out.println("Password " + password);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
