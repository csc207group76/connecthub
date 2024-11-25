package view;

import javax.swing.*;
import java.awt.*;

/**
 * The Search Bar located on the top of the page. The search bar is placed within the navigation bar
 */
public class SearchBar {
    public static JPanel createSearchPanel() {
        final JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(StyleConstants.HEADER_COLOR);

        final JTextField searchBar = new JTextField();
        searchBar.setBackground(StyleConstants.PANEL_COLOR);
        searchBar.setForeground(StyleConstants.TEXT_COLOR);
        searchBar.setCaretColor(StyleConstants.TEXT_COLOR);
        searchBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        final JButton searchButton = new JButton("Search");
        searchButton.setBackground(StyleConstants.BUTTON_COLOR);
        searchButton.setForeground(StyleConstants.TEXT_COLOR);

        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        return searchPanel;
    }
}
