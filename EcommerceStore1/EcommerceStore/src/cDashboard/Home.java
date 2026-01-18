package cDashboard;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Home {

    public JPanel homePanel;
    private JLabel bannerLabel;
    private int currentBannerIndex = 0;
    private final ImageIcon[] banners;

    public Home() {
        homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBounds(200, 50, 980, 600);
        homePanel.setBackground(Color.WHITE);

        // 1. Banner Section
        banners = new ImageIcon[]{
                new ImageIcon("src/assets/Banner1.jpg"),
                new ImageIcon("src/assets/Banner2.jpg"),
                new ImageIcon("src/assets/Banner3.jpg")
        };

        bannerLabel = new JLabel();
        bannerLabel.setBounds(20, 20, 930, 200);
        bannerLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBannerImage(0);
        homePanel.add(bannerLabel);
        autoSlideBanner();

        // 2. Welcome Label
        JLabel welcome = new JLabel("Welcome to QuickBazar!");
        welcome.setFont(new Font("Arial", Font.BOLD, 24));
        welcome.setBounds(20, 230, 400, 40);
        homePanel.add(welcome);

        // 3. Trending Categories
        JLabel catLabel = new JLabel("Trending Categories");
        catLabel.setFont(new Font("Arial", Font.BOLD, 18));
        catLabel.setBounds(20, 280, 300, 30);
        homePanel.add(catLabel);

        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        categoriesPanel.setBounds(20, 310, 940, 100);
        categoriesPanel.setBackground(new Color(245, 245, 245));

        String[] categories = {"Electronics", "Clothing", "Footwear", "Home Decor", "Watches", "Mobile Accessories"};
        for (String cat : categories) {
            JPanel card = createCategoryCard(cat);
            categoriesPanel.add(card);
        }
        homePanel.add(categoriesPanel);

        // 4. Featured Products Preview
        JLabel featLabel = new JLabel("Featured Products");
        featLabel.setFont(new Font("Arial", Font.BOLD, 18));
        featLabel.setBounds(20, 420, 300, 30);
        homePanel.add(featLabel);

        JPanel featureGrid = new JPanel();
        featureGrid.setLayout(new GridLayout(1, 3, 10, 10));
        featureGrid.setBounds(20, 460, 940, 120);
        featureGrid.setBackground(Color.WHITE);

        for (int i = 1; i <= 3; i++) {
            JPanel productCard = createProductPreview("Product " + i, 2500 + i * 500);
            featureGrid.add(productCard);
        }
        homePanel.add(featureGrid);
    }

    private void setBannerImage(int index) {
        Image image = banners[index].getImage().getScaledInstance(930, 200, Image.SCALE_SMOOTH);
        bannerLabel.setIcon(new ImageIcon(image));
    }

    private void autoSlideBanner() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentBannerIndex = (currentBannerIndex + 1) % banners.length;
                setBannerImage(currentBannerIndex);
            }
        }, 3000, 3000); // Slide every 3 seconds
    }

    private JPanel createCategoryCard(String name) {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(140, 80));
        card.setBackground(new Color(255, 255, 255));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        card.add(nameLabel, BorderLayout.CENTER);
        return card;
    }

    private JPanel createProductPreview(String title, int price) {
        JPanel card = new JPanel(null);
        card.setBackground(new Color(250, 250, 250));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel img = new JLabel("Img");
        img.setOpaque(true);
        img.setBackground(Color.LIGHT_GRAY);
        img.setBounds(10, 10, 100, 80);
        card.add(img);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBounds(120, 10, 150, 20);
        card.add(titleLabel);

        JLabel priceLabel = new JLabel("Rs. " + price);
        priceLabel.setBounds(120, 40, 150, 20);
        card.add(priceLabel);

        return card;
    }
}
