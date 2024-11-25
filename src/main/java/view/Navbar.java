package view;

import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Navigation Bar (top side of the homepage)
 */
public class Navbar {

    private final JPanel navBar;
    private final HomepageViewModel homePageViewModel;
    private final HomepageController homepageController;

    public Navbar(JPanel mainContent, HomepageViewModel homePageViewModel, HomepageController homepageController) {
        this.navBar = initializeNavBar(mainContent);
        this.homePageViewModel = homePageViewModel;
        this.homepageController = homepageController;
    }

    /**
     * Creates and initializes the navigation bar panel.
     *
     * @param mainContent the main content panel to enable navigation between views.
     * @return the initialized JPanel for the navigation bar.
     */
    private JPanel initializeNavBar(JPanel mainContent) {
        final JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(StyleConstants.HEADER_COLOR);
        navBar.setPreferredSize(new Dimension(800, 50));

        // Title label
        final JLabel titleLabel = new JLabel("Connect Hub");
        titleLabel.setForeground(StyleConstants.TEXT_COLOR);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // When hovering over the title, the cursor changes to hand, and clicking navigates to the homepage.
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                homepageController.fetchAllPosts();
                homepageController.switchToHomePageView();
            }
        });
        titleLabel.setFont(StyleConstants.HEADER_FONT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(
                StyleConstants.BORDER_TOP, StyleConstants.BORDER_LEFT,
                StyleConstants.BORDER_BOTTOM, StyleConstants.BORDER_RIGHT));
        navBar.add(titleLabel, BorderLayout.WEST);

        // Add search bar
        final JPanel searchPanel = SearchBar.createSearchPanel();
        navBar.add(searchPanel, BorderLayout.CENTER);

        // Add profile button
        final JButton profileButton = ProfileButton.createProfileButton();
        navBar.add(profileButton, BorderLayout.EAST);

        return navBar;
    }

    public JPanel getNavBar() {
        return this.navBar;
    }
}
