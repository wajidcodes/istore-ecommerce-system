package sDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import Project1.src.com.accounts.*;
import Accounts.sAccount;
import cDashboard.ViewProduct;
import HelpCenter.Help;

public class S_Dashboard extends JFrame {

    // Objects Of other Classes
    SMenu menu = new SMenu();
    SearchBar searchBar = new SearchBar();
    SOverviewPanel sOverviewPanel;
    sProductPanel sproductPanel;
    addProductPanel productPanel;
    odersPanel ordersPanel = new odersPanel();
    AddEventPanel events;
    sAccount account;
    Setting setting = new Setting();
    Help help = new Help();
    SupplierOrder order;

    JFrame frame;

    public S_Dashboard(String supplierID) {
        productPanel = new addProductPanel(supplierID);
        sproductPanel = new sProductPanel(supplierID);
        account = new sAccount(supplierID);
        order = new SupplierOrder(supplierID);
        sOverviewPanel = new SOverviewPanel(supplierID);
        events = new AddEventPanel(supplierID);
        components();
    }

    private void components(){
        //---Label Actions ----
        menu.MenuLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sOverviewPanel.setVisible(true);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
            }
        });

        menu.MenuLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(true);
                sproductPanel.showProduct();
                productPanel.productPanel_ToAdd.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
            }
        });

        sproductPanel.addProductButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                productPanel.productPanel_ToAdd.setVisible(true);
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
            }
        });

        menu.MenuLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ordersPanel.orderPanel.setVisible(true);
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
                order.setVisible(false);
            }
        });

        menu.MenuLabel4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ordersPanel.orderPanel.setVisible(false);
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                events.setVisible(true);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
                order.setVisible(false);
            }
        });

        menu.MenuLabel5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(true);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
                order.setVisible(false);
            }
        });

        menu.MenuLabel6.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setting.settingPanel.setVisible(true);
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                order.setVisible(false);
            }
        });

        menu.MenuLabel8.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                frame.dispose();
                new LoginForm();
            }
        });

        menu.MenuLabel7.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
                productPanel.productPanel_ToAdd.setVisible(false);
                ordersPanel.orderPanel.setVisible(false);
                events.setVisible(false);
                account.S_Account.setVisible(false);
                setting.settingPanel.setVisible(false);
                help.panel.setVisible(true);
                order.setVisible(false);
            }
        });

        menu.MenuLabel3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                order.setVisible(true);
                ordersPanel.orderPanel.setVisible(false);
                account.S_Account.setVisible(false);
                help.panel.setVisible(false);
                setting.settingPanel.setVisible(false);
                events.setVisible(false);
                sOverviewPanel.setVisible(false);
                sproductPanel.productPanel.setVisible(false);
            }
        });


        // ---- create a frame---
        frame = new JFrame();
        frame.setTitle("Sellar Dashboard");
        frame.setSize(1200, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);
        ImageIcon iconFrame = new ImageIcon(ClassLoader.getSystemResource("images/store2.png"));
        Image iconFrame1 = iconFrame.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon iconFrame1f = new ImageIcon(iconFrame1);
        frame.setIconImage(iconFrame1f.getImage());
        frame.getContentPane().setBackground(new Color(255,255,255));
        frame.add(searchBar.searchIcon);
        frame.add(menu.MenuPanel);
        frame.add(searchBar.searchPanel);
        frame.add(sOverviewPanel);
        frame.add(sproductPanel.productPanel);
        frame.add(productPanel.productPanel_ToAdd);
        frame.add(ordersPanel.orderPanel);
        frame.add(events);
        frame.add(account.S_Account);
        sOverviewPanel.setVisible(true);
        frame.add(help.panel);
        frame.add(order);
        frame.setVisible(true);
    }
}
