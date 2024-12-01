package view;

import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreatePostButton {

    private final JButton createPostButton;
    private final HomepageViewModel homePageViewModel;
    private final HomepageController homepageController;

    public CreatePostButton(JPanel mainContent, HomepageViewModel homePageViewModel, HomepageController homepageController) {
        this.createPostButton = initializeButton(mainContent);
        this.homePageViewModel = homePageViewModel;
        this.homepageController = homepageController;
    }

    private JButton initializeButton(JPanel mainContent) {
        final JButton createPostButton = new JButton("Create New Post");
        createPostButton.setBackground(Color.BLUE);
        createPostButton.setForeground(Color.BLUE);


        createPostButton.setFont(new Font("Arial", Font.BOLD, 14));
        createPostButton.setFocusPainted(false);

        // Set the size of the button for better appearance
        createPostButton.setPreferredSize(new Dimension(200, 50));

        createPostButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Change cursor on hover

        return createPostButton;
    }

    public JButton getCreatePostButton() {
        return this.createPostButton;
    }



}
