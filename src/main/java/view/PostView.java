package view;

import controller.homepage.HomepageController;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostState;
import controller.post.PostViewModel;
import controller.signup.SignupState;
import entity.Comment;
import use_case.getpost.GetPostInputBoundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.util.List;

public class PostView extends JPanel implements PropertyChangeListener {
    private final String viewName = "post";
    private final PostController postController;
    private final PostViewModel postViewModel;
    
    private final String FONT_TYPE = "Arial";
    private final JPanel mainContent = new JPanel(new BorderLayout());
    private final JLabel postTitle = new JLabel();
    private final JLabel commentTitle = new JLabel();
    private final JTextArea postContent = new JTextArea();
    private final JPanel commentsPanel = new JPanel();
    private final HomepageViewModel homePageViewModel;
    private final HomepageController homepageController;

    public PostView(PostController postController, PostViewModel postViewModel,
                    HomepageViewModel homePageViewModel, HomepageController homepageController) {
        this.postController = postController;
        this.homepageController = homepageController;
        this.postViewModel = postViewModel;
        this.homePageViewModel = homePageViewModel;

        postViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());
        setBackground(new Color(120, 133, 133)); // TODO change to constants HEADER

        // Add nav bar
        final JPanel navBar = new Navbar(mainContent, homePageViewModel, homepageController).getNavBar();
        add(navBar, BorderLayout.NORTH);

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


        // Comment title panel
        JPanel commentTitlePanel = new JPanel(new BorderLayout());
        JLabel commentTitle = new JLabel("Comment Section");
        commentTitle.setFont(new Font(FONT_TYPE, Font.BOLD, 16));
        commentTitle.setForeground(Color.DARK_GRAY);
        commentTitle.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        commentTitlePanel.add(commentTitle, BorderLayout.WEST);

        // Create commentButton
        JButton createCommentButton = new JButton("Add Comment");
        createCommentButton.setForeground(Color.WHITE);
        createCommentButton.setFont(new Font(FONT_TYPE, Font.BOLD, 14));
        createCommentButton.setBackground(new Color(0, 123, 255));
        commentTitlePanel.add(createCommentButton, BorderLayout.EAST);

        // Comments Panel
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBackground(Color.WHITE);
        commentsPanel.add(commentTitlePanel);

        // CreateCommonButton actionListener
        createCommentButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        postController.switchToCreateCommentView(postViewModel.getState());
            }
        });

        /*JPanel commentControlPanel = new JPanel(new BorderLayout());
        commentControlPanel.setBackground(Color.WHITE);*/
            
        mainContent.add(titlePanel, BorderLayout.NORTH);
        mainContent.add(new JScrollPane(postContent), BorderLayout.CENTER);
        mainContent.add(new JScrollPane(commentsPanel), BorderLayout.SOUTH);
        add(mainContent);
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
        //this.commentsPanel.removeAll();

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