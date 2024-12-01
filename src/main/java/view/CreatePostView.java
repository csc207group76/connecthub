package view;

import controller.create_post.CreatePostController;
import controller.create_post.CreatePostState;
import controller.create_post.CreatePostViewModel;
import controller.homepage.HomepageController;
import entity.Comment;
import entity.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class CreatePostView extends JPanel implements java.beans.PropertyChangeListener {

    private final String viewName = "create post";

    private JTextField titleField;
    private JTextArea contentField;
    private JComboBox<String> categoryDropdown;
    private JButton saveButton;
    private JButton cancelButton;

    private final CreatePostViewModel createPostViewModel;
    private final CreatePostController createPostController;
    private final HomepageController homepageController;

    public createPostView(CreatePostViewModel createPostViewModel, CreatePostController createPostController, HomepageController homepageController) {
        this.createPostViewModel = createPostViewModel;
        this.createPostController = createPostController;
        this.homepageController = homepageController;

        createPostViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray for modern aesthetic

        JLabel title = new JLabel("Create a Post");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(33, 37, 41)); // Dark gray
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Title:"), gbc);

        titleField = new JTextField();
        titleField.setFont(new Font("Arial", Font.PLAIN, 16));
        titleField.setBorder(new RoundedBorder(10));
        gbc.gridx = 1;
        inputPanel.add(titleField, gbc);

        // Category Dropdown
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Category:"), gbc);

        categoryDropdown = new JComboBox<>(new String[]{"Education", "Politics", "Sports", "Random", "Java"});
        categoryDropdown.setPreferredSize(new Dimension(200, 50));
        categoryDropdown.setFont(new Font("Arial", Font.PLAIN, 16));
        categoryDropdown.setBorder(new RoundedBorder(10));
        categoryDropdown.setBackground(Color.WHITE);
        gbc.gridx = 1;
        inputPanel.add(categoryDropdown, gbc);

        // Content Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Content:"), gbc);

        contentField = new JTextArea(5, 20);
        contentField.setFont(new Font("Arial", Font.PLAIN, 14));
        contentField.setLineWrap(true);
        contentField.setWrapStyleWord(true);
        contentField.setBorder(new RoundedBorder(10));
        JScrollPane contentScrollPane = new JScrollPane(contentField);
        gbc.gridx = 1;
        inputPanel.add(contentScrollPane, gbc);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Match background

        saveButton = createStyledButton("Save", new Color(76, 175, 80));
        cancelButton = createStyledButton("Cancel", new Color(220, 53, 69));

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createHorizontalGlue());

        add(title, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addPostTitleListener();
        addPostContentListener();
        addSaveButtonListener();
        addCancelButtonListener();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.DARK_GRAY);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private void addPostTitleListener() {
        titleField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreatePostState currentState = createPostViewModel.getState();
                currentState.setPostTitle(titleField.getText());
                createPostViewModel.setState(currentState);
            }

            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addPostContentListener() {
        contentField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreatePostState currentState = createPostViewModel.getState();
                currentState.setContent(contentField.getText());
                createPostViewModel.setState(currentState);
            }

            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addSaveButtonListener() {
        saveButton.addActionListener(e -> {
            String author = homepageController.fetchUser().getUsername();
            String content = contentField.getText();
            String attachmentPath = "";
            String fileType = "text";
            int dislikes = 0;
            int likes = 0;
            String postTitle = titleField.getText();
            List<User> moderators = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();
            String category = (String) categoryDropdown.getSelectedItem();

            if (postTitle.isEmpty() || content.isEmpty() || author.isEmpty() || ((String) categoryDropdown.getSelectedItem()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title, content, category and author are required!");
                return;
            }

            createPostController.execute(homepageController.fetchUser().getUserID(), content, attachmentPath, fileType, dislikes, likes, postTitle, moderators, comments, category);
            JOptionPane.showMessageDialog(this, "Post created successfully!");
            SwingUtilities.getWindowAncestor(this).dispose();
            homepageController.fetchAllPosts();
            createPostController.switchToHomePageview();
        });
    }

    private void addCancelButtonListener() {
        cancelButton.addActionListener(e -> handleCancelAction());
    }

    private void handleCancelAction() {
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel? All unsaved data will be lost.", "Cancel Post Creation", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            SwingUtilities.getWindowAncestor(this).dispose();
            createPostController.switchToHomePageview();
        }
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        final CreatePostState state = (CreatePostState) evt.getNewValue();
//        if (state.getPostTitle() != null) {
//            JOptionPane.showMessageDialog(null, "Test");
//        }
    }

    public String getViewName() {
        return viewName;
    }

    // Rounded border helper class
    static class RoundedBorder implements Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius, radius, radius, radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
