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
import java.util.ArrayList;

public class createPostView extends JPanel implements java.beans.PropertyChangeListener {

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
            java.util.List<User> moderators = new ArrayList<>();
            java.util.List<Comment> comments = new ArrayList<>();
            String category = (String) categoryDropdown.getSelectedItem();

            if (postTitle.isEmpty() || content.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Title, content, and author are required!");
                return;
            }

            createPostController.execute(homepageController.fetchUser().getUserID(), content, attachmentPath, fileType, dislikes, likes, postTitle, moderators, comments, category);
            JOptionPane.showMessageDialog(this, "Post created successfully!");
            SwingUtilities.getWindowAncestor(this).dispose();
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
}//package view;
//
//import controller.create_post.CreatePostController;
//import controller.create_post.CreatePostState;
//import controller.create_post.CreatePostViewModel;
//import controller.homepage.HomepageController;
//import entity.Comment;
//import entity.User;
//
//import javax.swing.*;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.util.ArrayList;
//
//public class createPostView extends JPanel implements PropertyChangeListener {
//
//    private final String viewName = "create post";
//
//    private JTextField titleField;
//    private JTextArea contentField;
//    private JComboBox<String> categoryDropdown;
//    private JButton saveButton;
//    private JButton cancelButton;
//
//    private final CreatePostViewModel createPostViewModel;
//    private final CreatePostController createPostController;
//    private final HomepageController homepageController;
//
//
//    public createPostView(CreatePostViewModel createPostViewModel, CreatePostController createPostController, HomepageController homepageController) {
//        this.createPostViewModel = createPostViewModel;
//        this.createPostController = createPostController;
//        this.homepageController = homepageController;
//
//        createPostViewModel.addPropertyChangeListener(this);
//
//        setSize(400, 300);
//        setLayout(new BorderLayout());
//        setBackground(new Color(120, 133, 133));
//
//        final JLabel title = new JLabel("Creating Post");
//        title.setFont(new Font("Arial", Font.BOLD, 18));
//        title.setForeground(Color.DARK_GRAY);
//        title.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        // Input Fields
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new GridLayout(3, 2));
//        inputPanel.setBackground(new Color(120, 133, 133)); // Match background
//
//
//        inputPanel.add(new JLabel("Title: "));
//        titleField = new JTextField();
//        titleField.setFont(new Font("Arial", Font.BOLD, 16));
//        titleField.setForeground(Color.DARK_GRAY);
//        titleField.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//        inputPanel.add(titleField);
//
//        inputPanel.add(new JLabel("Category: "));
//        categoryDropdown = new JComboBox<>(new String[]{"Education", "Politics", "Sports", "Random", "Java"});
//        categoryDropdown.setFont(new Font("Arial", Font.BOLD, 16));
//        categoryDropdown.setForeground(Color.DARK_GRAY);
//        categoryDropdown.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
//        categoryDropdown.setBackground(Color.WHITE); // Optional: Set background color for better visibility
//        inputPanel.add(categoryDropdown);
//
//        inputPanel.add(new JLabel("Content: "));
//        contentField = new JTextArea(5, 20);
//        contentField.setFont(new Font("Arial", Font.PLAIN, 14));
//        contentField.setForeground(Color.BLACK);
//        contentField.setLineWrap(true);
//        contentField.setWrapStyleWord(true);
//        contentField.setBackground(new Color(239, 241, 243));
//        contentField.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
//
//        inputPanel.add(new JScrollPane(contentField));
//
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.add(title);
//        mainPanel.add(inputPanel);
//
//        // Buttons Panel
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
//        buttonPanel.setBackground(new Color(120, 133, 133)); // Match background
//        buttonPanel.add(saveButton = new JButton("Save"));
//        buttonPanel.add(cancelButton = new JButton("Cancel"));
//
//        mainPanel.add(buttonPanel);
//
//        add(mainPanel, BorderLayout.CENTER);
//
//
//        // TODO: Correct order here?, I added the button listeners as
//        //  seperate functions because it got really messy here
//        addPostTitleListener();
//        addPostContentListener();
//        addSaveButtonListener();
//        addCancelButtonListener();
//
//    }
//
//    public String getTitleInput() {
//        return titleField.getText();
//    }
//
//    public String getContentInput() {
//        return contentField.getText();
//    }
//
//    // Real time updating the title and content, took it from the signup view
//
//    private void addPostTitleListener() {
//        titleField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final CreatePostState currentState = createPostViewModel.getState();
//                currentState.setPostTitle(titleField.getText());  // Update the title in the state
//                createPostViewModel.setState(currentState);  // Set the updated state back
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called when text is inserted
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called when text is removed
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called for attribute changes (rarely needed for plain text input)
//            }
//        });
//    }
//
//    // Similarly, add for content field or other input fields
//    private void addPostContentListener() {
//        contentField.getDocument().addDocumentListener(new DocumentListener() {
//
//            private void documentListenerHelper() {
//                final CreatePostState currentState = createPostViewModel.getState();
//                currentState.setContent(contentField.getText());  // Update content in the state
//                createPostViewModel.setState(currentState);  // Set the updated state back
//            }
//
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called when text is inserted
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called when text is removed
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                documentListenerHelper();  // Called for attribute changes (rarely needed for plain text input)
//            }
//        });
//    }
//
//
//    private void addSaveButtonListener() {
//        saveButton.addActionListener(e -> {
//            // Gather input data from the user interface
//            String author = homepageController.fetchUser().getUsername(); // TODO: username not name right?
//            String content = contentField.getText();
//            String attachmentPath = ""; // TODO: What do we use here
//            String fileType = "text"; // Assume fileType is determined elsewhere
//            int dislikes = 0; // Default value
//            int likes = 0; // Default value
//            String postTitle = titleField.getText();
//            java.util.List moderators = new ArrayList<>(); // TODO: Are these two correct -> done
//            java.util.List comments = new ArrayList<>();
//            String category = (String) categoryDropdown.getSelectedItem();
//
//            // Validate required inputs
//            // TODO: Not sure how to test if category is null is it categoryDropdown.getSelectedItem() = null?
//            if (postTitle.isEmpty() || content.isEmpty() || author.isEmpty() ) {
//                JOptionPane.showMessageDialog(this, "Title, content, and author are required!");
//                return;
//            }
//
//            // Pass the input data to the controller
//            createPostController.execute(homepageController.fetchUser().getUserID(), content, attachmentPath, fileType, dislikes, likes, postTitle, (java.util.List<User>) moderators, (java.util.List<Comment>) comments, category);
//            // Provide user feedback and switch to the homepage
//            JOptionPane.showMessageDialog(this, "Post created successfully!");
//            SwingUtilities.getWindowAncestor(this).dispose();
//            homepageController.fetchAllPosts();
//            createPostController.switchToHomePageview();
//        });
//    }
//
//
//    private void addCancelButtonListener() {
//        cancelButton.addActionListener(e -> handleCancelAction());
//    }
//
//    private void handleCancelAction() {
//        int option = JOptionPane.showConfirmDialog(this,
//                "Are you sure you want to cancel? All unsaved data will be lost.",
//                "Cancel Post Creation", JOptionPane.YES_NO_OPTION);
//
//        if (option == JOptionPane.YES_OPTION) {
//            // Close the current CreatePostView and show the HomePageView
//            SwingUtilities.getWindowAncestor(this).dispose();
//            createPostController.switchToHomePageview();
//        }
//    }
//
////    public void resetFields() {
////        titleField.setText("");
////        contentField.setText("");
////    }
//
//    // TODO: Should I use author/user here instead
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        final CreatePostState state = (CreatePostState) evt.getNewValue();
//        if (state.getPostTitle() != null) {
//            JOptionPane.showMessageDialog(null, "Test");
//        }
//    }
//
//    public String getViewName() {
//        return viewName;
//    }
//}