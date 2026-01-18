package sDashboard;

import javax.swing.*;
import java.awt.*;

public class odersPanel {
    JScrollPane orderPanel;
    JPanel orderHeaderPanel;
    JLabel headingorderLabel;
    JPanel invoicesPanel;
    JLabel invoicesLabel;

    public odersPanel() {
        invoicesLabel = new JLabel("Invoices");
        invoicesLabel.setFont(new Font("Serif", Font.BOLD, 20));
        invoicesLabel.setBounds(10,10,50,30);

        invoicesPanel = new JPanel();
        invoicesPanel.setBounds(20,20,960,500);
        invoicesPanel.setLayout(null);

        headingorderLabel = new JLabel("Orders");
        headingorderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingorderLabel.setFont(new Font("Seria", Font.BOLD, 20));
        headingorderLabel.setBounds(0,0,100,50);
        headingorderLabel.setForeground(Color.BLACK);

        orderHeaderPanel = new JPanel();
        orderHeaderPanel.setPreferredSize(new Dimension(960, 600));
        orderHeaderPanel.setLayout(null);
        orderHeaderPanel.setBackground(Color.WHITE);
        orderHeaderPanel.add(headingorderLabel);

        orderPanel = new JScrollPane();
        orderPanel.setLayout(null);
        orderPanel.setBounds(200,50,1000,600);
        orderPanel.setBackground(new Color(0xFAF9F6));
        orderPanel.add(orderHeaderPanel);
        orderPanel.add(invoicesPanel);
        orderPanel.setVisible(true);
    }
}
