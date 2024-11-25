package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The Profile button.
 */
public class ProfileButton {
    public static JButton createProfileButton() {
        final JButton profileButton = new JButton("Profile");
        profileButton.setBackground(StyleConstants.BUTTON_COLOR);
        profileButton.setForeground(StyleConstants.TEXT_COLOR);

        // Create drop down menu that opens when the profile button is clicked
        final JPopupMenu profileMenu = new JPopupMenu();

        // Add settings option to the dropdown menu
        final JMenuItem settingsButton = new JMenuItem("Settings");
        settingsButton.addActionListener(e -> {
            // Dynamically get the parent frame for the dialog
            // Replace with settings/frame or logout frame
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(profileButton);
            JOptionPane.showMessageDialog(parentFrame, "Settings clicked");
        });
        profileMenu.add(settingsButton);

        // Add logout option to the dropdown menu
        final JMenuItem logoutButton = new JMenuItem("Logout");
        logoutButton.addActionListener(e -> {
            // Dynamically get the parent window for the dialog
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(profileButton);
            JOptionPane.showMessageDialog(parentFrame, "Logout clicked");
        });
        profileMenu.add(logoutButton);

        profileButton.addActionListener(e -> profileMenu.show(profileButton, 0, profileButton.getHeight()));

        return profileButton;
    }
}
