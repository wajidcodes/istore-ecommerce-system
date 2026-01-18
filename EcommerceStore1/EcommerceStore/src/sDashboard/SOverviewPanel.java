package sDashboard;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SOverviewPanel extends JPanel {

    JScrollPane overviewPanel;
    JPanel currentMMR;
    JLabel title1;
    JLabel rate1;
    JPanel cCostumerPanel;
    JLabel title2;
    JLabel rate2;
    JPanel activeCostumerPanel;
    JLabel title3;
    JLabel rate3;
    JPanel profitPanel;
    JLabel title4;
    JLabel rate4;
    JPanel orderPanel;
    JLabel titleOrdersCount;
    JLabel rateOrdersCount;
    JLabel titleOrdersSales;
    JLabel rateOrdersSales;
    JPanel eventOverviewPanel;
    JScrollPane recentMailsPanel;
    JPanel notificationsContainer; // Container for notifications inside scroll pane
    JLabel trendLabel;
    JLabel eventTitle;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/loginaccounts";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "YOUR_MYSQL_PASSWORD_HERE";

    public SOverviewPanel(String supplierId) {

        setLayout(null);
        setBounds(200, 50, 1000, 600);
        setBackground(new Color(0xFAF9F6));

        // ---Recent Mails--- setup with container panel for multiple labels
        notificationsContainer = new JPanel();
        notificationsContainer.setLayout(new BoxLayout(notificationsContainer, BoxLayout.Y_AXIS));
        notificationsContainer.setBackground(Color.WHITE);

        recentMailsPanel = new JScrollPane(notificationsContainer);
        recentMailsPanel.setBounds(20, 460, 940, 100);
        recentMailsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        recentMailsPanel.setBackground(Color.WHITE);
        recentMailsPanel.getVerticalScrollBar().setUnitIncrement(20);

        // ---Event Overview Panel---
        eventOverviewPanel = new JPanel();
        eventOverviewPanel.setBounds(740, 140, 220, 300);
        eventOverviewPanel.setBackground(Color.WHITE);
        eventOverviewPanel.setLayout(new BoxLayout(eventOverviewPanel, BoxLayout.Y_AXIS));

        eventTitle = new JLabel("Supplier Events");
        eventTitle.setFont(new Font("Roboto", Font.BOLD, 16));
        eventTitle.setForeground(Color.BLACK);
        eventTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        eventOverviewPanel.add(eventTitle);

        JScrollPane eventScrollPane = new JScrollPane(eventOverviewPanel);
        eventScrollPane.setBounds(740, 140, 220, 300);
        eventScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        eventScrollPane.getVerticalScrollBar().setUnitIncrement(20);

        // ---Orders Panel---
        titleOrdersCount = new JLabel("Total Orders");
        titleOrdersCount.setFont(new Font("Roboto", Font.BOLD, 16));
        titleOrdersCount.setForeground(Color.BLACK);
        titleOrdersCount.setBounds(10, 5, 200, 40);
        titleOrdersCount.setHorizontalAlignment(SwingConstants.LEFT);

        rateOrdersCount = new JLabel("0");
        rateOrdersCount.setBounds(10, 40, 200, 40);
        rateOrdersCount.setFont(new Font("Time New Roman", Font.BOLD, 24));
        rateOrdersCount.setForeground(Color.BLACK);
        rateOrdersCount.setHorizontalAlignment(SwingConstants.CENTER);

        titleOrdersSales = new JLabel("Total Sales");
        titleOrdersSales.setFont(new Font("Roboto", Font.BOLD, 16));
        titleOrdersSales.setForeground(Color.BLACK);
        titleOrdersSales.setBounds(10, 85, 200, 40);
        titleOrdersSales.setHorizontalAlignment(SwingConstants.LEFT);

        rateOrdersSales = new JLabel("$0");
        rateOrdersSales.setBounds(10, 120, 200, 40);
        rateOrdersSales.setFont(new Font("Time New Roman", Font.BOLD, 24));
        rateOrdersSales.setForeground(Color.BLACK);
        rateOrdersSales.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel salepic = new JLabel();
        salepic.setBounds(40, 170, 150, 120);
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("images/sale.png"));
        Image img1 = icon1.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
        ImageIcon icon1f = new ImageIcon(img1);
        salepic.setIcon(icon1f);

        orderPanel = new JPanel();
        orderPanel.setBounds(500, 140, 220, 300);
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setLayout(null);
        orderPanel.add(titleOrdersCount);
        orderPanel.add(rateOrdersCount);
        orderPanel.add(titleOrdersSales);
        orderPanel.add(rateOrdersSales);
        orderPanel.add(salepic);

        // ---Trend Panel---
        trendLabel = new JLabel("Trend");
        trendLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        trendLabel.setForeground(Color.BLACK);
        trendLabel.setBounds(10, 10, 100, 50);
        trendLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(5, 70, 450, 220);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/trend2.png"));
        Image img = icon.getImage().getScaledInstance(450, 220, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(img);
        imgLabel.setIcon(icon2);

        JPanel trendPanel = new JPanel();
        trendPanel.setBounds(20, 140, 460, 300);
        trendPanel.setBackground(Color.WHITE);
        trendPanel.setLayout(null);
        trendPanel.add(trendLabel);
        trendPanel.add(imgLabel);

        // ---Profit Panel---
        rate4 = new JLabel("0");
        rate4.setBounds(10, 20, 200, 90);
        rate4.setFont(new Font("Time New Roman", Font.BOLD, 28));
        rate4.setForeground(Color.black);
        rate4.setHorizontalAlignment(SwingConstants.CENTER);
        rate4.setVerticalAlignment(SwingConstants.CENTER);

        title4 = new JLabel("Total Profit");
        title4.setFont(new Font("Roboto", Font.BOLD, 16));
        title4.setForeground(Color.black);
        title4.setBounds(10, 5, 200, 40);
        title4.setHorizontalAlignment(SwingConstants.LEFT);

        profitPanel = new JPanel();
        profitPanel.setBounds(740, 20, 220, 100);
        profitPanel.setBackground(new Color(0xF95738));
        profitPanel.setLayout(null);
        profitPanel.add(title4);
        profitPanel.add(rate4);

        // ---Active Customers Panel---
        rate3 = new JLabel("0");
        rate3.setBounds(10, 20, 200, 90);
        rate3.setFont(new Font("Time New Roman", Font.BOLD, 28));
        rate3.setForeground(Color.black);
        rate3.setHorizontalAlignment(SwingConstants.CENTER);
        rate3.setVerticalAlignment(SwingConstants.CENTER);

        title3 = new JLabel("Active Customers");
        title3.setFont(new Font("Roboto", Font.BOLD, 16));
        title3.setForeground(Color.black);
        title3.setBounds(10, 5, 200, 40);
        title3.setHorizontalAlignment(SwingConstants.LEFT);

        activeCostumerPanel = new JPanel();
        activeCostumerPanel.setBounds(500, 20, 220, 100);
        activeCostumerPanel.setBackground(new Color(0xEE964B));
        activeCostumerPanel.setLayout(null);
        activeCostumerPanel.add(title3);
        activeCostumerPanel.add(rate3);

        // ---Current Customers Panel---
        rate2 = new JLabel("589");
        rate2.setBounds(10, 20, 200, 90);
        rate2.setFont(new Font("Time New Roman", Font.BOLD, 28));
        rate2.setForeground(Color.black);
        rate2.setHorizontalAlignment(SwingConstants.CENTER);
        rate2.setVerticalAlignment(SwingConstants.CENTER);

        title2 = new JLabel("Current Customers");
        title2.setFont(new Font("Roboto", Font.BOLD, 16));
        title2.setForeground(Color.black);
        title2.setBounds(10, 5, 200, 40);
        title2.setHorizontalAlignment(SwingConstants.LEFT);

        cCostumerPanel = new JPanel();
        cCostumerPanel.setBounds(260, 20, 220, 100);
        cCostumerPanel.setBackground(new Color(0xF4D35E));
        cCostumerPanel.setLayout(null);
        cCostumerPanel.add(title2);
        cCostumerPanel.add(rate2);

        // ---Current MMR Panel---
        rate1 = new JLabel("$0");
        rate1.setFont(new Font("Time New Roman", Font.BOLD, 28));
        rate1.setBounds(10, 20, 200, 90);
        rate1.setHorizontalAlignment(SwingConstants.CENTER);
        rate1.setVerticalAlignment(SwingConstants.CENTER);
        rate1.setForeground(Color.BLACK);

        title1 = new JLabel("Current MMR");
        title1.setFont(new Font("Roboto", Font.BOLD, 16));
        title1.setForeground(Color.BLACK);
        title1.setBounds(10, 5, 200, 40);
        title1.setHorizontalAlignment(SwingConstants.LEFT);
        title1.setVerticalAlignment(SwingConstants.CENTER);

        currentMMR = new JPanel();
        currentMMR.setBounds(20, 20, 220, 100);
        currentMMR.setBackground(new Color(0xEBEBD3));
        currentMMR.setLayout(null);
        currentMMR.add(title1);
        currentMMR.add(rate1);

        // ---Overview Panel---
        overviewPanel = new JScrollPane();
        overviewPanel.setBounds(0, 0, 1000, 600);
        overviewPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        overviewPanel.getVerticalScrollBar().setUnitIncrement(20);
        overviewPanel.setBackground(new Color(0xFAF9F6));

        JPanel container = new JPanel();
        container.setLayout(null);
        container.setPreferredSize(new Dimension(1000, 600));
        container.setBackground(new Color(0xFAF9F6));

        container.add(currentMMR);
        container.add(cCostumerPanel);
        container.add(activeCostumerPanel);
        container.add(profitPanel);
        container.add(trendPanel);
        container.add(orderPanel);
        container.add(eventScrollPane);
        container.add(recentMailsPanel);

        overviewPanel.setViewportView(container);
        add(overviewPanel);

        // Load dashboard data
        loadDashboardData(supplierId);
        loadEvents(supplierId);
        loadNotifications(supplierId);
    }

    private void loadDashboardData(String supplierId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            // Orders Count
            String orderCountQuery = "SELECT COUNT(*) AS order_count, IFNULL(SUM(price),0) AS total_sales FROM orders WHERE supplier_id = ?";
            PreparedStatement orderPst = conn.prepareStatement(orderCountQuery);
            orderPst.setString(1, supplierId);
            ResultSet orderRs = orderPst.executeQuery();
            if (orderRs.next()) {
                int orderCount = orderRs.getInt("order_count");
                int totalSales = orderRs.getInt("total_sales");

                rateOrdersCount.setText(String.valueOf(orderCount));
                rateOrdersSales.setText("$" + totalSales);
            }

            // Profit Sum (if separate from sales, else can omit or keep)
            String profitQuery = "SELECT IFNULL(SUM(price),0) AS total_profit FROM orders WHERE supplier_id = ?";
            PreparedStatement profitPst = conn.prepareStatement(profitQuery);
            profitPst.setString(1, supplierId);
            ResultSet profitRs = profitPst.executeQuery();
            if (profitRs.next()) {
                int totalProfit = profitRs.getInt("total_profit");
                rate4.setText("$" + totalProfit);
            }

            // Active Customers count - assuming customers with orders for this supplier
            String activeCustQuery = "SELECT COUNT(DISTINCT user_email) AS active_customers FROM orders WHERE supplier_id = ?";
            PreparedStatement activeCustPst = conn.prepareStatement(activeCustQuery);
            activeCustPst.setString(1, supplierId);
            ResultSet activeCustRs = activeCustPst.executeQuery();
            if (activeCustRs.next()) {
                int activeCustomers = activeCustRs.getInt("active_customers");
                rate3.setText(String.valueOf(activeCustomers));
            }

            // Current Customers - you may define differently, here just a placeholder
            // String currentCustQuery = "SELECT COUNT(*) AS current_customers FROM
            // signupdetail WHERE supplier_id = ?";
            // PreparedStatement currentCustPst = conn.prepareStatement(currentCustQuery);
            // currentCustPst.setString(1, supplierId);
            // ResultSet currentCustRs = currentCustPst.executeQuery();
            // if (currentCustRs.next()) {
            // int currentCustomers = currentCustRs.getInt("current_customers");
            // rate2.setText(String.valueOf(currentCustomers));
            // }

            // Current MMR - placeholder, you can update logic
            rate1.setText("$" + (orderRs.next() ? orderRs.getInt("total_sales") : 0));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadEvents(String supplierId) {
        eventOverviewPanel.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT title, description, event_date FROM events WHERE supplier_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, supplierId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");

                // Panel per event
                JPanel eventPanel = new JPanel();
                eventPanel.setLayout(new BorderLayout());
                eventPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                eventPanel.setMaximumSize(new Dimension(200, 80));
                eventPanel.setBackground(Color.WHITE);

                JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
                titleLabel.setFont(new Font("Roboto", Font.BOLD, 14));

                JLabel dateLabel = new JLabel(eventDate.toString());
                dateLabel.setFont(new Font("Roboto", Font.PLAIN, 12));
                dateLabel.setForeground(Color.DARK_GRAY);

                JTextArea descriptionArea = new JTextArea(description);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setEditable(false);
                descriptionArea.setFont(new Font("Roboto", Font.PLAIN, 12));
                descriptionArea.setBackground(Color.WHITE);

                eventPanel.add(titleLabel, BorderLayout.NORTH);
                eventPanel.add(dateLabel, BorderLayout.WEST);
                eventPanel.add(descriptionArea, BorderLayout.CENTER);

                eventOverviewPanel.add(eventPanel);
                eventOverviewPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
            }

            eventOverviewPanel.revalidate();
            eventOverviewPanel.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadNotifications(String supplierId) {
        notificationsContainer.removeAll();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            String sql = "SELECT message, timestamp FROM notifications WHERE supplier_id = ? ORDER BY timestamp DESC";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, supplierId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String message = rs.getString("message");
                Timestamp timestamp = rs.getTimestamp("timestamp");

                JPanel singleNotificationPanel = new JPanel();
                singleNotificationPanel.setLayout(new BorderLayout());
                singleNotificationPanel.setMaximumSize(new Dimension(900, 50));
                singleNotificationPanel.setBackground(Color.WHITE);
                singleNotificationPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                JLabel msgLabel = new JLabel(message);
                msgLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
                msgLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JLabel timeLabel = new JLabel(timestamp.toString());
                timeLabel.setFont(new Font("Roboto", Font.ITALIC, 12));
                timeLabel.setForeground(Color.GRAY);
                timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                timeLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 10));

                singleNotificationPanel.add(msgLabel, BorderLayout.WEST);
                singleNotificationPanel.add(timeLabel, BorderLayout.EAST);

                notificationsContainer.add(singleNotificationPanel);
            }

            notificationsContainer.revalidate();
            notificationsContainer.repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
