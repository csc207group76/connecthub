package view;

import controller.delete_post.DeletePostController;
import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostPresenter;
import controller.post.PostState;
import controller.post.PostViewModel;
import daos.DBUserDataAccessObject;
import entity.Comment;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import use_case.get_user.GetUserInputBoundary;
import use_case.get_user.GetUserInteractor;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.util.List;

import static view.StyleConstants.POST_WIDTH;

public class PostView extends JPanel implements PropertyChangeListener {
    private final String viewName = "post";
    private final PostController postController;
    private final PostViewModel postViewModel;

    private final String FONT_TYPE = "Arial";
    private final JPanel mainContent = new JPanel(new BorderLayout());
    private final JLabel postTitle = new JLabel();
    private final JTextArea postContent = new JTextArea();
    private final JPanel commentsPanel = new JPanel();
    private final HomepageViewModel homePageViewModel;
    private final HomepageController homepageController;
    private final PostPresenter postPresenter;

    // button for options
    private final JButton optionsButton = new JButton("⋮");  // had to lookup the three dots thing online
    private final JPopupMenu optionsMenu = new JPopupMenu();
    private final DeletePostController deletePostController;
    private final DBUserDataAccessObject userRepo;




    public PostView(PostController postController, PostViewModel postViewModel,
                    HomepageViewModel homePageViewModel, HomepageController homepageController,
                    DeletePostController deletePostController,
                    DBUserDataAccessObject userRepo,
                    PostPresenter postPresenter) {
        this.postController = postController;
        this.homepageController = homepageController;
        this.postViewModel = postViewModel;
        this.homePageViewModel = homePageViewModel;
        this.deletePostController = deletePostController;
        this.userRepo = userRepo;
        this.postPresenter = postPresenter;

        postViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBackground(new Color(120, 133, 133)); // TODO change to constants HEADER

        // Add nav bar
        final JPanel navBar = new Navbar(mainContent, homePageViewModel, homepageController).getNavBar();
        add(navBar, BorderLayout.NORTH);

        // Back button (won't implement this for now)
        // JButton backButton = new JButton("Back");
        // backButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        // backButton.setBackground(new Color(200, 200, 200));
        // backButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        // backButton.addActionListener(e -> {
        //     CardLayout layout = (CardLayout) mainContent.getLayout();
        //     layout.show(mainContent, "Homepage");
        // });

        // titlePanel.add(backButton, BorderLayout.EAST);

        // Post title
        JPanel titlePanel = new JPanel(new BorderLayout());
        postTitle.setFont(new Font(FONT_TYPE, Font.BOLD, 16));
        postTitle.setForeground(Color.DARK_GRAY);
        postTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        titlePanel.add(postTitle, BorderLayout.WEST);

        // Post content
        postContent.setFont(new Font(FONT_TYPE, Font.PLAIN, 14));
        postContent.setForeground(Color.BLACK);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBackground(new Color(239, 241, 243));
        postContent.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Post comment
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.WHITE);
        commentsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Comments",
                0,
                0,
                new Font(FONT_TYPE, Font.BOLD, 14),
                Color.DARK_GRAY));

        mainContent.add(titlePanel, BorderLayout.NORTH);
        mainContent.add(new JScrollPane(postContent), BorderLayout.CENTER);
        mainContent.add(new JScrollPane(commentsPanel), BorderLayout.SOUTH);
        add(mainContent);

        // the button for options
        JButton optionsButton = new JButton("⋮");
        JPopupMenu optionsMenu = new JPopupMenu();
        JMenuItem deletePostItem = new JMenuItem("Delete Post");

        optionsMenu.add(deletePostItem);

        int buttonWidth = 30;
        int buttonHeight = 30;
        optionsButton.setBounds(POST_WIDTH - buttonWidth - 10, 10, buttonWidth, buttonHeight);
        titlePanel.add(optionsButton);

        optionsButton.addActionListener(e -> optionsMenu.show(optionsButton, 0, optionsButton.getHeight()));

        deletePostItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    optionsButton,
                    "Are you sure you want to delete?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                GetUserInputBoundary getUserInteractor = new GetUserInteractor(userRepo, new CommonUserFactory());
                User currentUser = getUserInteractor.getCurrentUser();

                if (currentUser == null) {
                    JOptionPane.showMessageDialog(this, "Current user could not be retrieved. Cannot delete the post.");
                    return;
                }

                String currentUserId = currentUser.getUserID();
                PostState state = postViewModel.getState();
                String postId = state.getPostID();

                if (postId == null || postId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Post ID is missing. Cannot delete the post.");
                    return;
                }
                if (currentUserId == null || currentUserId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Current user ID is missing. Cannot delete the post.");
                    return;
                }

                String authorId = deletePostController.getAuthorId(postId);

                if (authorId == null || authorId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Author ID could not be determined. Cannot delete the post.");
                    return;
                }

                boolean success = deletePostController.deletePost(postId, currentUserId, authorId);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Post successfully deleted.");
                    postPresenter.switchToHomePageView();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete the post. Please try again.");
                }
            }
        });

        }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final PostState state = (PostState) evt.getNewValue();
        this.setPostTitle(state);
        this.setPostContent(state);
        this.setComments(state);

        if (state.getPostContentError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostContentError());
        } else if (state.getCommentsError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostContent());
        }
    }

    private void setPostTitle(PostState state) {
        this.postTitle.setText(state.getPostTitle());
    }

    private void setPostContent(PostState state) {
        this.postContent.setText(state.getPostContent());
    }

    private void setComments(PostState state) {
        this.commentsPanel.removeAll();

        List<Comment> comments = state.getComments();
        for (Comment comment : comments) {
            JLabel commentLabel = new JLabel(comment.getContent().getBody());
            commentLabel.setFont(new Font(FONT_TYPE, Font.PLAIN, 12));
            commentLabel.setForeground(Color.DARK_GRAY);
            commentLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            // TODO Add content texts from the comment
            commentsPanel.add(commentLabel);
        }
    }
}