package view;

import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * The Navigation Pane
 */
public class NavigationPane {

    private final JPanel navigationPane;
    private final HomepageViewModel homePageViewModel;
    private final HomepageController homepageController;

    public NavigationPane(JPanel mainContent, HomepageViewModel homePageViewModel,
                          HomepageController homepageController) {
        this.navigationPane = createNavigationPane(mainContent);
        this.homePageViewModel = homePageViewModel;
        this.homepageController = homepageController;
    }

    private JPanel createNavigationPane(JPanel homepage) {
        final JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(StyleConstants.PANEL_COLOR);
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        navigationPanel.setPreferredSize(new Dimension(130, homepage.getHeight()));

        JButton[] categoryButtons = {
                new JButton("All Posts"),
                new JButton("Java"),
                new JButton("Education"),
                new JButton("Politics"),
                new JButton("Sports"),
                new JButton("Random")
        };
        for (JButton button : categoryButtons) {
            if (button.getText().equals("All Posts")) {
                button.addActionListener(e -> {
                    homepageController.fetchAllPosts();
                    homepageController.switchToHomePageView();
                });
            } else {
                button.addActionListener(e -> {
                    String category = button.getText();
                    System.out.println(category);
                    homepageController.getPostsByCategory(category);
                    homepageController.switchToHomePageView();
                });
            }
            button.setBackground(StyleConstants.BUTTON_COLOR);
            button.setForeground(StyleConstants.TEXT_COLOR);
            button.setFocusPainted(false);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            navigationPanel.add(Box.createVerticalStrut(10));
            navigationPanel.add(button);
        }

        return navigationPanel;
    }

    public JPanel getNavigationPane() {
        return this.navigationPane;
    }
}
