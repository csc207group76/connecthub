package view;

import controller.homepage.HomepageController;
import controller.post.PostController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Posts displayed on the homepage are shown in individual PostBoxes
 */
public class PostBox implements ActionListener {
    private JPanel postBox;
    private String title;
    private String content;
    private JPanel mainContent;
    private String postId;

    public PostBox(String title, String content, String postId, JPanel mainContent, 
                   HomepageController homepageController, PostController postController) {
        this.title = title;
        this.content = content;
        this.mainContent = mainContent; // Initialize mainContent here
        this.postId = postId;

        this.postBox = new JPanel();
        postBox.setLayout(new BorderLayout());
        postBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        postBox.setBackground(Color.WHITE);
        postBox.setPreferredSize(new Dimension(StyleConstants.POST_WIDTH, StyleConstants.POST_HEIGHT));

        // Post title
        final JLabel postTitle = new JLabel(this.title);
        postTitle.setForeground(Color.DARK_GRAY);
        postTitle.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        postBox.add(postTitle, BorderLayout.NORTH);

        // Post content
        final JTextArea postContent = new JTextArea(this.content);
        postContent.setFont(new Font(StyleConstants.FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);

        // GREYISH BACKGROUND FOR EACH POST CHANGE LATER
        postContent.setBackground(new Color(245, 245, 245));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5))); // padding
        postBox.add(postContent, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Spacing from content
        buttonPanel.setBackground(Color.WHITE);

        final JButton likeButton = createStyledButton("Like");
        final JButton dislikeButton = createStyledButton("Dislike");
        final JButton viewPostButton = createStyledButton("View Post");
        
        viewPostButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    postController.execute(postId);
                    homepageController.switchToLoginView();
                }
            }
        );


        likeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Liked post " + postId));
        dislikeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Disliked post " + postId));

        buttonPanel.add(likeButton);
        buttonPanel.add(dislikeButton);
        buttonPanel.add(viewPostButton);

        postBox.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getPostBox() {
        return this.postBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // JOptionPane.showMessageDialog(this, "hi");
    }

    private static JButton createStyledButton(String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font(StyleConstants.FONT_TYPE, Font.PLAIN, 12));
        // Light gray background for button
        button.setBackground(new Color(230, 230, 230));
        button.setForeground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                // Padding inside button
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        // Removes the box appearing when clicking on button
        button.setFocusPainted(false);
        return button;
    }


}
