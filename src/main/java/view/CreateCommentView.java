package view;

import controller.createComment.CreateCommentState;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostViewModel;
import controller.createComment.CreateCommentController;
import controller.createComment.CreateCommentViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateCommentView extends JPanel implements PropertyChangeListener {
    private final String viewName = "create comment";
    private final JTextArea commentInputArea = new JTextArea(5, 30);
    private final JButton submitButton;
    private final JButton cancelButton;
    private final JPanel mainContent = new JPanel(new BorderLayout());

    private final String FONT_TYPE = "Arial";
    private final CreateCommentController creaetCommentController;
    private final CreateCommentViewModel createCommentViewModel;
    private final HomepageViewModel homepageViewModel;
    private final HomepageController homepageController;
    private final PostViewModel postViewModel;
    private final PostController postController;

    public CreateCommentView(CreateCommentController createCommentController, CreateCommentViewModel createCommentViewModel,
                             HomepageViewModel homePageViewModel, HomepageController homepageController,
                             PostViewModel postViewModel, PostController postController) {

        this.creaetCommentController = createCommentController;
        this.createCommentViewModel = createCommentViewModel;
        this.homepageViewModel = homePageViewModel;
        this.homepageController = homepageController;
        this.postViewModel = postViewModel;
        this.postController = postController;

        setLayout(new BorderLayout());
        setBackground(new Color(120, 133, 133));

        // Add nav bar
        final JPanel navBar = new Navbar(mainContent, homePageViewModel, homepageController).getNavBar();
        add(navBar, BorderLayout.NORTH);

        // Main content panel
        commentInputArea.setFont(new Font(FONT_TYPE, Font.PLAIN, 14));
        commentInputArea.setForeground(Color.BLACK);
        commentInputArea.setLineWrap(true);
        commentInputArea.setWrapStyleWord(true);
        commentInputArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        JScrollPane commentScrollPane = new JScrollPane(commentInputArea);
        commentScrollPane.setBorder(BorderFactory.createTitledBorder("Write your comment:"));
        mainContent.add(commentScrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        submitButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);

        cancelButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);

        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);

        mainContent.add(buttonPanel, BorderLayout.SOUTH);

        add(mainContent, BorderLayout.CENTER);

        cancelButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        postController.switchBack(createCommentViewModel);
                    }
        });

        submitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {String commentText = commentInputArea.getText().trim(); // Remove leading/trailing whitespace
                        if (commentText.isEmpty()) {
                            // Show error if comment is empty
                            JOptionPane.showMessageDialog(mainContent, "Comment cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            // Handle the comment submission
                            final CreateCommentState currentState = createCommentViewModel.getState();
                            createCommentController.execute(
                                    currentState.getEntryID(),
                                    currentState.getAuthor(),
                                    currentState.getContent(),
                                    currentState.getCommentDate(),
                                    currentState.getAttchmentPath(),
                                    currentState.getFilepath());
                            // Switch back to the PostView
                            postController.switchBack(createCommentViewModel);
                        }
                    }
        });
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
