package cDashboard;

import sDashboard.SMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavigationBar extends SMenu {

    public NavigationBar() {
        getMenuPanel().setBackground(new Color(0xFDC5F5));
        getLogoPanel().setBackground(new Color(0xFDC5F5));

        getLogoLabel().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cShoppingstoreLogo.png")));

        getMenuLabel1().setText("Home");
        getMenuLabel1().setForeground(Color.BLACK);
        getMenuLabel1().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel1().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/home.png")));
        getMenuLabel1().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });

        getMenuLabel2().setText("Events");
        getMenuLabel2().setForeground(Color.BLACK);
        getMenuLabel2().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel2().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cEvent.png")));

        getMenuLabel3().setText("Explore Product");
        getMenuLabel3().setForeground(Color.BLACK);
        getMenuLabel3().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel3().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cExploreProduct.png")));

        getMenuLabel4().setText("My Carts");
        getMenuLabel4().setForeground(Color.BLACK);
        getMenuLabel4().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel4().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cMyCarts.png")));

        getMenuLabel5().setText("My Orders");
        getMenuLabel5().setForeground(Color.BLACK);
        getMenuLabel5().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel5().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cMyOrders.png")));

        getMenuLabel6().setText("Notify");
        getMenuLabel6().setForeground(Color.BLACK);
        getMenuLabel6().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel6().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cBalance.png")));

        getMenuLabel7().setText("Help Center");
        getMenuLabel7().setForeground(Color.BLACK);
        getMenuLabel7().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel7().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cHelpCenter.png")));

        getMenuLabel8().setText("Log out");
        getMenuLabel8().setForeground(Color.BLACK);
        getMenuLabel8().setFont(new Font("SansSerif", Font.PLAIN, 12));
        getMenuLabel8().setIcon(new ImageIcon(ClassLoader.getSystemResource("images/cLogOut.png")));
    }
}
