package Admin;

import sDashboard.*;
import sDashboard.SMenu;
import sDashboard.SearchBar;
import Project1.src.com.accounts.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class admin {
    SMenu menu = new SMenu();
    SearchBar searchBar = new SearchBar();
    displaySupplier displaySupplier = new displaySupplier();
    displayCustomer displayCustomer = new displayCustomer();
    Applications application = new Applications();
    JPanel customerPanel;
    JFrame frame;
    public admin() {

        searchBar.getSearchPanel().setVisible(true);
        searchBar.getSearchIcon().setVisible(true);
        searchBar.getSearchIcon().setBounds(10,10,30,30);

        menu.getMenuPanel().setVisible(true);
        menu.getMenuPanel().setBackground(new Color(0xF3E37C));
        menu.getLogoPanel().setVisible(true);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/adminlogo.png"));
        Image img = icon.getImage().getScaledInstance(200,150,Image.SCALE_SMOOTH);
        ImageIcon logo = new ImageIcon(img);
        menu.getLogoLabel().setIcon(logo);

        menu.getMenuLabel1().setText("Admin");
        menu.getMenuLabel1().setForeground(Color.BLACK);
        menu.getMenuLabel1().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

            }
        });

        menu.getMenuLabel2().setText("Suppliers");
        menu.getMenuLabel2().setForeground(Color.BLACK);
        menu.getMenuLabel2().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displaySupplier.supplierPanel.setVisible(true);
                displayCustomer.customerPanel.setVisible(false);
                application.APanel.setVisible(false);
            }
        });
        menu.getMenuLabel3().setText("Customers");
        menu.getMenuLabel3().setForeground(Color.BLACK);
        menu.getMenuLabel3().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                displayCustomer.customerPanel.setVisible(true);
                displaySupplier.supplierPanel.setVisible(false);
                application.APanel.setVisible(false);
            }
        });
        menu.getMenuLabel4().setText("Applications");
        menu.getMenuLabel4().setForeground(Color.BLACK);
        menu.getMenuLabel4().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                application.APanel.setVisible(true);
                displayCustomer.customerPanel.setVisible(false);
                displaySupplier.supplierPanel.setVisible(false);
            }
        });
        menu.getMenuLabel5().setText("Updates");
        menu.getMenuLabel5().setForeground(Color.BLACK);

        menu.getMenuLabel6().setText("Settings");
        menu.getMenuLabel6().setForeground(Color.BLACK);

        menu.getMenuLabel7().setText("History");
        menu.getMenuLabel7().setForeground(Color.BLACK);

        menu.getMenuLabel8().setText("Logout");
        menu.getMenuLabel8().setForeground(Color.BLACK);
        menu.getMenuLabel8().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new LoginForm().setVisible(true);
            }
        });


        frame = new JFrame();
        frame.setTitle("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 650);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.add(menu.getMenuPanel());
        frame.add(searchBar.getSearchPanel());
        frame.add(displaySupplier.supplierPanel);
        frame.add(displayCustomer.customerPanel);
        frame.add(application.APanel);
        frame.setVisible(true);
    }

}
