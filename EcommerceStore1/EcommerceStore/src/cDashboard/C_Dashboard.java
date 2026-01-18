// C_Dashboard.java
package cDashboard;

import Project1.src.com.accounts.LoginForm;
import sDashboard.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Project1.src.com.accounts.*;
import Accounts.cAccount;
import HelpCenter.Help;

public class C_Dashboard {

    NavigationBar navigationBar = new NavigationBar();
    searchBar searchBar = new searchBar();
    Home home = new Home();
    cAccount account;
    ExploreProduct exploreProduct;
    Events events;
    Help h;
    MyCarts myCarts;
    Orders ordersPanel;
    JFrame cFrame;
    CustomerNotifySupplierPanel notify;

    public C_Dashboard(String email) {
        account = new cAccount(email);
        exploreProduct = new ExploreProduct(email, this);
        h = new Help();
        myCarts = new MyCarts(email, this);
        ordersPanel = new Orders(email);
        notify = new CustomerNotifySupplierPanel(email);
        events = new Events();

        navigationBar.getMenuLabel3().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                exploreProduct.exploreProductPanel.setVisible(true);
            }
        });

        navigationBar.getMenuLabel8().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cFrame.setVisible(false);
                cFrame.dispose();
                new LoginForm().setVisible(true);
            }
        });

        searchBar.getOptionIcon().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                account.C_Account.setVisible(true);
            }
        });

        navigationBar.getMenuLabel7().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                h.panel.setVisible(true);
            }
        });

        navigationBar.getMenuLabel1().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                home.homePanel.setVisible(true);
            }
        });

        navigationBar.getMenuLabel2().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                events.setVisible(true);
            }
        });

        navigationBar.getMenuLabel4().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                myCarts.myCartsPanel.setVisible(true);
                myCarts.l
                cFrame.revalidate();
                cFrame.repaint();
            }
        });

        navigationBar.getMenuLabel5().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                ordersPanel.setVisible(true);
            }
        });

        navigationBar.getMenuLabel6().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                hideAllPanels();
                notify.setVisible(true);
            }
        });

        cFrame = new JFrame();
        cFrame.setTitle("Dashboard");
        cFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cFrame.setLayout(null);
        cFrame.setResizable(false);
        cFrame.setSize(1200, 650);
        cFrame.add(navigationBar.getMenuPanel());
        cFrame.add(searchBar.getSearchPanel());
        cFrame.add(home.homePanel);
        cFrame.add(account.C_Account);
        cFrame.add(exploreProduct.exploreProductPanel);
        cFrame.add(h.panel);
        cFrame.add(events);
        cFrame.add(myCarts.myCartsPanel);
        cFrame.add(ordersPanel);
        cFrame.add(notify);
        cFrame.setVisible(true);
    }

//    private void hideAllPanels() {
//        for (Component comp : cFrame.getContentPane().getComponents()) {
//            if (comp != navigationBar.getMenuPanel() && comp != searchBar.getSearchPanel()) {
//                comp.setVisible(false);
//                if (comp instanceof ViewProduct) {
//                    cFrame.remove(comp);
//                }
//            }
//        }
//        cFrame.repaint();
//    }

    public void hideAllPanels() {
        home.homePanel.setVisible(false);
        account.C_Account.setVisible(false);
        exploreProduct.exploreProductPanel.setVisible(false);
        events.setVisible(false);
        h.panel.setVisible(false);
        ordersPanel.setVisible(false);
        notify.setVisible(false);
        if (myCarts != null) myCarts.myCartsPanel.setVisible(false);

        // Remove all ViewProduct panels if any
        for (Component comp : cFrame.getContentPane().getComponents()) {
            if (comp instanceof ViewProduct) {
                cFrame.remove(comp);
            }
        }
    }



    public void showProductPanel(ViewProduct viewProduct) {
        hideAllPanels();
        viewProduct.setBounds(200, 50, 980, 570);
        cFrame.add(viewProduct);
        viewProduct.setVisible(true);
        cFrame.revalidate();
        cFrame.repaint();
    }

    public void showPlaceOrderPanel(PlaceOrder placeOrderPanel) {
        hideAllPanels();

        // Remove any ViewProduct panels from frame (if present)
        for (Component comp : cFrame.getContentPane().getComponents()) {
            if (comp instanceof ViewProduct) {
                cFrame.remove(comp);
            }
        }

        cFrame.getContentPane().add(placeOrderPanel);
        placeOrderPanel.setVisible(true);
        placeOrderPanel.setBounds(200, 50, 980, 600);

        cFrame.revalidate();
        cFrame.repaint();
    }
}
