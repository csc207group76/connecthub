package view;

import controller.create_post.CreatePostController;
import controller.create_post.CreatePostState;
import controller.create_post.CreatePostViewModel;
import controller.signup.SignupController;
import controller.signup.SignupState;
import controller.signup.SignupViewModel;
import entity.Comment;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class createPostView extends JDialog implements ActionListener, PropertyChangeListener {
    private JTextField titleField;
    private JTextArea contentField;
    private JTextField categoryField;
    private JButton saveButton;
    private JButton cancelButton;
    private final CreatePostViewModel createPostViewModel;
    private final CreatePostController createPostController;


    public createPostView(JFrame parentFrame, CreatePostViewModel createPostViewModel, CreatePostController createPostController) {
        super(parentFrame, "Create Post", true);
        this.createPostViewModel = createPostViewModel;
        this.createPostController = createPostController;

        createPostViewModel.addPropertyChangeListener(this);


        setSize(400, 300);
        setLayout(new BorderLayout());

        final JLabel title = new JLabel("Creating Post");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input Fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Title: "));
        titleField = new JTextField();
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Content: "));
        contentField = new JTextArea(5, 20);
        inputPanel.add(new JScrollPane(contentField));
        add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        saveButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(saveButton)) {
                            final CreatePostState currentState = createPostViewModel.getState();

                            createPostController.execute(currentState.getAuthor(), currentState.getContent(), null,
                                    null, currentState.getDislikes(), currentState.getLikes(), currentState.getPostTitle(),
                                    currentState.getModerators(), currentState.getComments(), currentState.getCategory());
                            // TODO change null to the actual values
                        }
                    }
                }
        );
        cancelButton.addActionListener(this);

        addSaveButtonListener();
        addCancelButtonListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        this.add(title);
//        this.add(fullnameInfo);
//        this.add(emailInfo);
//        this.add(birthdateInfo);
//        this.add(usernameInfo);
//        this.add(passwordInfo);
//        this.add(repeatPasswordInfo);
//        this.add(signUp);
//        this.add(buttons);
    }

    public String getTitleInput() {
        return titleField.getText();
    }

    public String getContentInput() {
        return contentField.getText();
    }


    private void addSaveButtonListener() {
        saveButton.addActionListener(e -> {
            // Gather input data from the user interface
//            String author = authorInputField.getText(); // TODO: not sure how to get this
            String content = contentField.getText();
            String attachmentPath = null; // TODO: What do we do here
            String fileType = "text"; // Assume fileType is determined elsewhere
            int dislikes = 0; // Default value
            int likes = 0; // Default value
            String postTitle = titleField.getText();
            List moderators = new List(); // TODO: Are these two correct
            List comments = new List();
            String category = categoryField.getText();

            // Validate required inputs
            // TODO: add the fact that author coudl be empty or null
            if (postTitle.isEmpty() || content.isEmpty() ) {
                JOptionPane.showMessageDialog(this, "Title, content, and author are required!");
                return;
            }

            // Pass the input data to the controller
            // TODO: not sure how to get author
            createPostController.execute(author, content, attachmentPath, fileType, dislikes, likes, postTitle, moderators, comments, category);
            // Provide user feedback and switch to the homepage
            JOptionPane.showMessageDialog(this, "Post created successfully!");
            createPostController.switchToHomePageview();
        });
    }


    private void addCancelButtonListener() {
        cancelButton.addActionListener(e -> handleCancelAction());
    }

    private void handleCancelAction() {
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel? All unsaved data will be lost.",
                "Cancel Post Creation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            // Close the current CreatePostView and show the HomePageView
            this.setVisible(false);  // Hide CreatePostView
            // TODO: How to show homepage here?
        }
    }

    public void resetFields() {
        titleField.setText("");
        contentField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreatePostState state = (CreatePostState) evt.getNewValue();
        if (state.getPostTitle() != null) {
            JOptionPane.showMessageDialog(this, state.getPostTitleError());
        }
    }
}