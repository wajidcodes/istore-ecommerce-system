package sDashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SupplierOrder extends JPanel {

    private JTable ordersTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private String supplierID;

    private static final String url = "jdbc:mysql://127.0.0.1:3306/loginaccounts";
    private static final String user = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD_HERE";

    public SupplierOrder(String supplierID) {
        this.supplierID = supplierID;
        setLayout(new BorderLayout());
        setBounds(200, 50, 980, 600);
        initComponents();
        loadOrdersForSupplier();
    }

    private void initComponents() {
        String[] columns = { "Order ID", "Customer Email", "Product Title", "Price", "Delivery Cost", "Full Name",
                "Address", "City", "Province", "Postal Code", "Order Date" };

        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ordersTable = new JTable(tableModel);
        ordersTable.setFillsViewportHeight(true);
        ordersTable.setRowHeight(50); // Optional: taller rows

        scrollPane = new JScrollPane(ordersTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadOrdersForSupplier() {
        tableModel.setRowCount(0);

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM orders WHERE supplier_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, supplierID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("order_id"),
                        rs.getString("user_email"),
                        rs.getString("product_title"),
                        rs.getDouble("price"),
                        rs.getDouble("delivery_cost"),
                        rs.getString("full_name"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("province"),
                        rs.getString("postal_code"),
                        rs.getTimestamp("order_date")
                };
                tableModel.addRow(row);
            }

            pst.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading supplier orders: " + e.getMessage());
        }
    }
}
