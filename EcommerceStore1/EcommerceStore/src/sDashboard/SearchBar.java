package sDashboard;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;

public class SearchBar {

    JPanel searchPanel;
    JLabel searchIcon;
    JTextField searchText;
    JLabel optionIcon;

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JLabel getSearchIcon() {
        return searchIcon;
    }

    public JTextField getSearchText() {
        return searchText;
    }

    public JLabel getOptionIcon() {
        return optionIcon;
    }

    public SearchBar() {
        // ---Option Icon---
        optionIcon = new JLabel();
        optionIcon.setBounds(930,0,50,50);
        ImageIcon optionIconImage = new ImageIcon(ClassLoader.getSystemResource("images/optionIcon.png"));
        Image optionIconImage2 = optionIconImage.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon optionIconImage3 = new ImageIcon(optionIconImage2);
        optionIcon.setIcon(optionIconImage3);
        optionIcon.setBackground(Color.WHITE);
        optionIcon.setHorizontalAlignment(SwingConstants.CENTER);
        optionIcon.setVerticalAlignment(SwingConstants.CENTER);
        optionIcon.setLayout(null);

        // ---Search Field---
        searchText = new JTextField();
        searchText.setBounds(50,5,880,40);
        searchText.setEditable(true);
        searchText.setHorizontalAlignment(JTextField.LEFT);
        searchText.setForeground(Color.BLACK);
        searchText.setBackground(Color.WHITE);
        searchText.setFont(new Font("Tahoma", Font.PLAIN, 18));
        searchText.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //---Search Icon---
        searchIcon = new JLabel();
        searchIcon.setBounds(201,0,50,50);
        searchIcon.setBackground(Color.WHITE);
        searchIcon.setLayout(null);
        searchIcon.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon searchIconImg = new ImageIcon(ClassLoader.getSystemResource("images/searchblack.png"));
        Image searchIconImg1 = searchIconImg.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        ImageIcon searchIconImg2 = new ImageIcon(searchIconImg1);
        searchIcon.setIcon(searchIconImg2);

        searchPanel = new JPanel();
        searchPanel.setBounds(201,5,1000,50);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        searchPanel.setLayout(null);
        searchPanel.add(searchIcon);
        searchPanel.add(searchText);
        searchPanel.add(optionIcon);
        searchPanel.setVisible(true);
    }
}
