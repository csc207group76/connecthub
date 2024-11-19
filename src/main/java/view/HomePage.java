package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * The Home Page view.
 */
public class HomePage {

    // Border for titles, labels, buttons
    public static final int BORDER_TOP = 5;
    public static final int BORDER_BOTTOM = 5;
    public static final int BORDER_LEFT = 15;
    public static final int BORDER_RIGHT = 0;

    // Program window dimensions
    public static final int APP_WIDTH = 1000;
    public static final int APP_HEIGHT = 600;

    // Post box
    public static final int POST_WIDTH = 700;
    public static final int POST_HEIGHT = 150;

    public static final int POST_TITLE_FONT_SIZE = 18;

    // Font to use
    public static final String FONT_TYPE = "Arial";

    public static void main(String[] args) {

        // Create the main frame
        final JFrame frame = new JFrame("Connect Hub");

        // Terminate program when you click the 'X' button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(APP_WIDTH, APP_HEIGHT);
        frame.setLayout(new BorderLayout());

        // Create a CardLayout for the main content area
        // Cardlayout makes sure that new "views" open in the current window
        // Added this so that when you open a post, it can be viewed in the same window
        final JPanel mainContent = new JPanel(new CardLayout());

        // Create individual views
        final JPanel homepageView = createHomepageView(mainContent);
        final JPanel postView = new JPanel(new BorderLayout());

        // Add views to the CardLayout
        mainContent.add(homepageView, "Homepage");
        mainContent.add(postView, "PostView");

        // Nav bar
        // The nav bar has the App name on the left, the search bar in the middle,
        // a search button to the right of the search box, and a profile button on the right most side
        final JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(Color.LIGHT_GRAY);
        navBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

        // Add the app name to the nav bar and allow users to click on it to go to the home page
        final JLabel titleLabel = new JLabel("Connect Hub");

        // TODO: Fix word "Connect Hub" is not centered within the JLabel despite Line 66
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Change the cursor to the pointing-hand upon hover
        // Line 69, 70 is from
        // https://stackoverflow.com/questions/16131811/clickable-text-in-a-jtextpane
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        titleLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                // Go back to the Homepage (Switch to the homepage card)
                final CardLayout cl = (CardLayout) mainContent.getLayout();
                cl.show(mainContent, "Homepage");
            }
        });
        titleLabel.setFont(new Font(FONT_TYPE, Font.BOLD, POST_TITLE_FONT_SIZE));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
        // Add title label to leftmost side of the navbar panel
        navBar.add(titleLabel, BorderLayout.WEST);

        // Search bar
        // Create a panel for the search bar then add this to the nav bar
        final JPanel searchPanel = new JPanel(new BorderLayout());
        final JTextField searchBar = new JTextField(20);
        final JButton searchButton = new JButton("Search");
        searchPanel.add(searchBar, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        // Add to nav bar
        navBar.add(searchPanel, BorderLayout.CENTER);

        // Profile button
        final JButton profileButton = new JButton("Profile");

        // Using lambda expression syntax
        // See https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java
        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Didn't make user profile view yet"));
        navBar.add(profileButton, BorderLayout.EAST);

        // Add navigation bar and main content to frame
        frame.add(navBar, BorderLayout.NORTH);
        frame.add(mainContent, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }

    // Create the homepage view
    private static JPanel createHomepageView(JPanel mainContent) {
        final JPanel homepage = new JPanel();
        homepage.setLayout(new BorderLayout());

        // Navigation panel for filtering
        final JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        navigationPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        navigationPanel.setPreferredSize(new Dimension(200, homepage.getHeight()));

        // TODO: Replace with helper that generates a new button per category
        final JButton allPostsButton = new JButton("All Posts");
        final JButton category1Button = new JButton("Category 1");
        final JButton category2Button = new JButton("Category 2");
        final JButton category3Button = new JButton("Category 3");
        navigationPanel.add(allPostsButton);
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(category1Button);
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(category2Button);
        navigationPanel.add(Box.createVerticalStrut(10));
        navigationPanel.add(category3Button);

        homepage.add(navigationPanel, BorderLayout.WEST);

        // Content area
        final JPanel contentArea = new JPanel();
        contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        contentArea.setBackground(Color.WHITE);

        // Add mock posts
        for (int i = 1; i <= 5; i++) {
            contentArea.add(createPostBox("Post Title " + i,
                            "content of post " + i, i, mainContent));
            // Add some space between posts
            contentArea.add(Box.createVerticalStrut(10));
        }

        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        homepage.add(scrollPane, BorderLayout.CENTER);

        return homepage;
    }

    // Create a post box
    private static JPanel createPostBox(String title, String content, int postId, JPanel mainContent) {
        final JPanel postBox = new JPanel();
        postBox.setLayout(new BorderLayout());
        postBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        postBox.setBackground(Color.WHITE);
        postBox.setPreferredSize(new Dimension(POST_WIDTH, POST_HEIGHT));

        // Post title
        final JLabel postTitle = new JLabel(title);
        postTitle.setFont(new Font(FONT_TYPE, Font.BOLD, 16));
        postTitle.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
        postBox.add(postTitle, BorderLayout.NORTH);

        // Post content
        final JTextArea postContent = new JTextArea(content);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
        postBox.add(postContent, BorderLayout.CENTER);

        // Buttons panel
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));

        final JButton likeButton = new JButton("Like");
        final JButton dislikeButton = new JButton("Dislike");
        final JButton viewPostButton = new JButton("View Post");

        likeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Liked post " + postId));
        dislikeButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Disliked post" + postId));

        // Replace content area with post view when clicked
        viewPostButton.addActionListener(e -> openPostView(mainContent, title, content));

        buttonPanel.add(likeButton);
        buttonPanel.add(dislikeButton);
        buttonPanel.add(viewPostButton);

        postBox.add(buttonPanel, BorderLayout.SOUTH);

        return postBox;
    }

    // Open post view within the same frame
    private static void openPostView(JPanel mainContent, String title, String content) {
        final JPanel postView = new JPanel(new BorderLayout());

        // Post title
        final JLabel postTitle = new JLabel(title);
        postTitle.setFont(new Font(FONT_TYPE, Font.BOLD, POST_TITLE_FONT_SIZE));
        postTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postView.add(postTitle, BorderLayout.NORTH);

        // Post content
        final JTextArea postContent = new JTextArea(content);
        postContent.setLineWrap(true);
        postContent.setWrapStyleWord(true);
        postContent.setEditable(false);
        postContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        postView.add(new JScrollPane(postContent), BorderLayout.CENTER);

        // Comments section
        final JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));

        // ADD DUMMY COMMENTS CHANGE THIS CODE LATER
        for (int i = 1; i <= 5; i++) {
            final JLabel comment = new JLabel("Comment " + i + ": This is a sample comment.");
            comment.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
            commentsPanel.add(comment);
        }

        final JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        postView.add(commentsScrollPane, BorderLayout.SOUTH);

        // Switch to post view
        final CardLayout layout = (CardLayout) mainContent.getLayout();
        mainContent.add(postView, "PostView");
        layout.show(mainContent, "PostView");
    }
}
