package cDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Orders extends JPanel {

    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    private String userEmail;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public Orders(String userEmail) {
        this.userEmail = userEmail;

        setLayout(new BorderLayout());
        setBounds(200, 50, 980, 600);
        initComponents();
        loadOrdersFromDatabase();
    }

    private void initComponents() {

        String[] columns = { "Order ID", "Product Title", "Price", "Supplier ID", "Delivery Cost", "Full Name",
                "Address", "City", "Province", "Postal Code", "Order Date" };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ordersTable = new JTable(tableModel);
        ordersTable.setFillsViewportHeight(true);

        ordersTable.setRowHeight(50);

        scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadOrdersFromDatabase() {
        tableModel.setRowCount(0);

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT order_id, product_title, price, supplier_id, delivery_cost, full_name, address, city, province, postal_code, order_date FROM orders WHERE user_email = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, userEmail);

            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] row = new Object[11];
                row[0] = rs.getInt("order_id");
                row[1] = rs.getString("product_title");
                row[2] = rs.getInt("price");
                row[3] = rs.getString("supplier_id");
                row[4] = rs.getInt("delivery_cost");
                row[5] = rs.getString("full_name");
                row[6] = rs.getString("address");
                row[7] = rs.getString("city");
                row[8] = rs.getString("province");
                row[9] = rs.getString("postal_code");
                row[10] = rs.getTimestamp("order_date");

                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading orders: " + e.getMessage(), "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (rs != null)
                    rs.close();
            } catch (Exception ignored) {
            }
            try {
                if (pst != null)
                    pst.close();
            } catch (Exception ignored) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception ignored) {
            }
        }
    }
}
