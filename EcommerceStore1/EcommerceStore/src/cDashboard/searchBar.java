package cDashboard;

import sDashboard.SearchBar;

import javax.swing.*;
import java.awt.*;

public class searchBar extends SearchBar {
    JLabel headingLabel;

    public searchBar() {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/cAccount.png"));
        Image iconImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon icon2 = new ImageIcon(iconImage);
        getOptionIcon().setIcon(icon2);
        getOptionIcon().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        headingLabel = new JLabel("Dashboard");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setVerticalAlignment(SwingConstants.CENTER);
        headingLabel.setForeground(Color.BLACK);
        headingLabel.setBounds(0,0,150,50);

        getSearchPanel().setBounds(200,0,1000,50);
        getSearchPanel().add(headingLabel);

        getSearchText().setBackground(Color.white);
        getSearchText().setForeground(Color.black);
        getSearchText().setFont(new Font("Serif", Font.PLAIN, 12));
        getSearchText().setBounds(580,10,350,30);

        getSearchIcon().setBounds(530,5,40,40);
    }
}
