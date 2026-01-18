package sDashboard;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class SMenu{
    JPanel LogoPanel;
    JPanel MenuPanel;
    JLabel LogoLabel;
    JLabel MenuLabel1;
    JLabel MenuLabel2;
    JLabel MenuLabel3;
    JLabel MenuLabel4;
    JLabel MenuLabel5;
    JLabel MenuLabel6;
    JLabel MenuLabel7;
    JLabel MenuLabel8;

    public JPanel getLogoPanel() {
        return LogoPanel;
    }

    public JPanel getMenuPanel() {
        return MenuPanel;
    }

    public JLabel getLogoLabel() {
        return LogoLabel;
    }

    public JLabel getMenuLabel1() {
        return MenuLabel1;
    }

    public JLabel getMenuLabel2() {
        return MenuLabel2;
    }

    public JLabel getMenuLabel3() {
        return MenuLabel3;
    }

    public JLabel getMenuLabel4() {
        return MenuLabel4;
    }

    public JLabel getMenuLabel5() {
        return MenuLabel5;
    }

    public JLabel getMenuLabel6() {
        return MenuLabel6;
    }

    public JLabel getMenuLabel7() {
        return MenuLabel7;
    }

    public JLabel getMenuLabel8() {
        return MenuLabel8;
    }

    public SMenu() {

        //--- Menu item 1 ---
        MenuLabel1 = new JLabel("OverView");
        MenuLabel1.setForeground(Color.WHITE);
        MenuLabel1.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel1.setFocusable(false);
        MenuLabel1.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel1.setVerticalAlignment(JLabel.CENTER);
        MenuLabel1.setIconTextGap(15);
        ImageIcon MenuIcon1 = new ImageIcon(ClassLoader.getSystemResource("./images/apps.png"));
        Image Menu1Icon1 = MenuIcon1.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon1f = new ImageIcon(Menu1Icon1);
        MenuLabel1.setIcon(MenuIcon1f);
        MenuLabel1.setBounds(25, 160, 150, 40);
        MenuLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- Menu Item 2 ---
        MenuLabel2 = new JLabel("Products");
        MenuLabel2.setForeground(Color.WHITE);
        MenuLabel2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel2.setFocusable(false);
        MenuLabel2.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel2.setVerticalAlignment(JLabel.CENTER);
        MenuLabel2.setIconTextGap(15);
        ImageIcon MenuIcon2 = new ImageIcon(ClassLoader.getSystemResource("./images/shopping-cart24px.png"));
        Image Menu1Icon2 = MenuIcon2.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon2f = new ImageIcon(Menu1Icon2);
        MenuLabel2.setIcon(MenuIcon2f);
        MenuLabel2.setBounds(25, 200, 150, 40);
        MenuLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ---Menu item 3---
        MenuLabel3 = new JLabel("Orders");
        MenuLabel3.setForeground(Color.WHITE);
        MenuLabel3.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel3.setFocusable(false);
        MenuLabel3.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel3.setVerticalAlignment(JLabel.CENTER);
        MenuLabel3.setIconTextGap(15);
        ImageIcon MenuIcon3 = new ImageIcon(ClassLoader.getSystemResource("./images/order.png"));
        Image Menu1Icon3 = MenuIcon3.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon3f = new ImageIcon(Menu1Icon3);
        MenuLabel3.setIcon(MenuIcon3f);
        MenuLabel3.setBounds(25, 240, 150, 40);
        MenuLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));


        // ---Menu item 4---
        MenuLabel4 = new JLabel("Add Events");
        MenuLabel4.setForeground(Color.WHITE);
        MenuLabel4.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel4.setFocusable(false);
        MenuLabel4.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel4.setVerticalAlignment(JLabel.CENTER);
        MenuLabel4.setIconTextGap(15);
        ImageIcon MenuIcon4 = new ImageIcon(ClassLoader.getSystemResource("./images/document.png"));
        Image Menu1Icon4 = MenuIcon4.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon4f = new ImageIcon(Menu1Icon4);
        MenuLabel4.setIcon(MenuIcon4f);
        MenuLabel4.setBounds(25, 280, 150, 40);
        MenuLabel4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // ---Menu item 5---
        MenuLabel5 = new JLabel("Account");
        MenuLabel5.setForeground(Color.WHITE);
        MenuLabel5.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel5.setFocusable(false);
        MenuLabel5.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel5.setVerticalAlignment(JLabel.CENTER);
        MenuLabel5.setIconTextGap(15);
        ImageIcon MenuIcon5 = new ImageIcon(ClassLoader.getSystemResource("./images/user.png"));
        Image Menu1Icon5 = MenuIcon5.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon5f = new ImageIcon(Menu1Icon5);
        MenuLabel5.setIcon(MenuIcon5f);
        MenuLabel5.setBounds(25, 320, 150, 40);
        MenuLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //--- Menu item 6---
        MenuLabel6 = new JLabel("Setting");
        MenuLabel6.setForeground(Color.WHITE);
        MenuLabel6.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel6.setFocusable(false);
        MenuLabel6.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel6.setVerticalAlignment(JLabel.CENTER);
        MenuLabel6.setIconTextGap(15);
        ImageIcon MenuIcon6 = new ImageIcon(ClassLoader.getSystemResource("./images/settings.png"));
        Image Menu1Icon6 = MenuIcon6.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon6f = new ImageIcon(Menu1Icon6);
        MenuLabel6.setIcon(MenuIcon6f);
        MenuLabel6.setBounds(25, 360, 150, 40);
        MenuLabel6.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //--- Menu Item 7---
        MenuLabel7 = new JLabel("Help");
        MenuLabel7.setForeground(Color.WHITE);
        MenuLabel7.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel7.setFocusable(false);
        MenuLabel7.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel7.setVerticalAlignment(JLabel.CENTER);
        MenuLabel7.setIconTextGap(15);
        ImageIcon MenuIcon7 = new ImageIcon(ClassLoader.getSystemResource("./images/interrogation.png"));
        Image Menu1Icon7 = MenuIcon7.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon7f = new ImageIcon(Menu1Icon7);
        MenuLabel7.setIcon(MenuIcon7f);
        MenuLabel7.setBounds(25, 400, 150, 40);
        MenuLabel7.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //---Menu item 8---
        MenuLabel8 = new JLabel("Log out");
        MenuLabel8.setForeground(Color.WHITE);
        MenuLabel8.setFont(new Font("SansSerif", Font.PLAIN, 12));
        MenuLabel8.setFocusable(false);
        MenuLabel8.setHorizontalAlignment(JLabel.LEFT);
        MenuLabel8.setVerticalAlignment(JLabel.CENTER);
        MenuLabel8.setIconTextGap(15);
        ImageIcon MenuIcon8 = new ImageIcon(ClassLoader.getSystemResource("./images/interrogation.png"));
        Image Menu1Icon8 = MenuIcon8.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
        ImageIcon MenuIcon8f = new ImageIcon(Menu1Icon8);
        MenuLabel8.setIcon(MenuIcon8f);
        MenuLabel8.setBounds(25, 550, 150, 40);
        MenuLabel8.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //----Logo Label -----
        LogoLabel = new JLabel();
        LogoLabel.setBackground(new Color(0x083D77));
        LogoLabel.setBounds(0, 0, 200, 150);
        LogoLabel.setLayout(null);
        LogoLabel.setHorizontalAlignment(JLabel.CENTER);
        LogoLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon LogoImage = new ImageIcon(ClassLoader.getSystemResource("images/store2.png"));
        Image LogoImage1 = LogoImage.getImage().getScaledInstance(200, 150, Image.SCALE_DEFAULT);
        ImageIcon LogoImage1f = new ImageIcon(LogoImage1);
        LogoLabel.setIcon(LogoImage1f);


        LogoPanel = new JPanel();
        LogoPanel.setBounds(0, 0, 200, 150);
        LogoPanel.setBackground(new Color(0x083D77));
        LogoPanel.setLayout(null);
        LogoPanel.add(LogoLabel);

        MenuPanel = new JPanel();
        MenuPanel.setBounds(0, 0, 200, 650);
        MenuPanel.setBackground(new Color(0x083D77));
        MenuPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        MenuPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        MenuPanel.setLayout(null);
        MenuPanel.add(LogoPanel);
        MenuPanel.add(MenuLabel1);
        MenuPanel.add(MenuLabel2);
        MenuPanel.add(MenuLabel3);
        MenuPanel.add(MenuLabel4);
        MenuPanel.add(MenuLabel5);
        MenuPanel.add(MenuLabel6);
        MenuPanel.add(MenuLabel7);
        MenuPanel.add(MenuLabel8);
    }
}
