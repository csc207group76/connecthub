package view;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import controller.homepage.HomepageController;
import controller.homepage.HomepageState;
import controller.homepage.HomepageViewModel;
import controller.post.PostController;
import controller.post.PostState;
import entity.Post;
import java.util.List;

/**
 * The View for the Home Page.
 */
public class HomePageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home page";

    private final JPanel mainContent = new JPanel(new BorderLayout());
    private final JPanel homepage = new JPanel();
    private final JPanel contentArea = new JPanel();
    private final JPanel rightPaddingPanel = new JPanel();

    private final HomepageController homepageController;
    private final PostController postController;
    private final HomepageViewModel homepageViewModel;

    public HomePageView(HomepageController homepageController, PostController postController, HomepageViewModel homePageViewModel) {
        this.homepageController = homepageController;
        this.postController = postController;
        this.homepageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        this.homepage.setLayout(new BorderLayout());
        this.homepage.setBackground(StyleConstants.BACKGROUND_COLOR);
        
        // Add nav bar
        final JPanel navBar = new Navbar(homepage, this.homepageViewModel, this.homepageController).getNavBar();
        mainContent.add(navBar, BorderLayout.NORTH);

        // Add navigation pane
        final JPanel navigationPanel = NavigationPane.createNavigationPane(homepage);
        homepage.add(navigationPanel, BorderLayout.WEST);

        // Add center panel to display post previews
        this.contentArea.setLayout(new BoxLayout(contentArea, BoxLayout.Y_AXIS));
        this.contentArea.setBackground(Color.WHITE);

        // Scroll bar
        final JScrollPane scrollPane = new JScrollPane(contentArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 550));
        homepage.add(scrollPane, BorderLayout.CENTER);

        // Add right panel to center posts and also leave space for future features
        this.rightPaddingPanel.setBackground(StyleConstants.PANEL_COLOR);
        this.rightPaddingPanel.setPreferredSize(new Dimension(147, homepage.getHeight()));
        homepage.add(rightPaddingPanel, BorderLayout.EAST);

        populatePosts();

        mainContent.add(homepage);
        add(mainContent);

        // // Example of observing changes from ViewModel
        // homePageViewModel.addPropertyChangeListener(evt -> {
        //     // Update UI based on new state (e.g., posts updated)
        //     HomepageState state = homePageViewModel.getState();
        //     if (state.getPosts() != null) {
        //         contentArea.removeAll();
        //         for (String post : state.getPosts()) {
        //             contentArea.add(new JLabel(post)); // Simplified example
        //         }
        //         contentArea.revalidate();
        //         contentArea.repaint();
        //     }
        // });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HomepageState state = (HomepageState) evt.getNewValue();
        this.loadPosts(state.getPosts());

        if (state.getPostsError() != null) {
            JOptionPane.showMessageDialog(this, state.getPostsError());
        } else if (state.getCurrentUserError() != null) {
            JOptionPane.showMessageDialog(this, state.getCurrentUserError());
        } 
    }

    public String getViewName() {
        return viewName;
    }
    
    private void populatePosts() {
        this.homepageController.fetchAllPosts();
    }
    
    private void loadPosts(List<Post> posts) {
        this.contentArea.removeAll();
        for (Post post : posts) {
            this.contentArea.add(new PostBox(
                post.getPostTitle(), post.getContent().getBody(), 
                post.getEntryID(), homepage, homepageController, postController).getPostBox());
        }
    }
    // For testing purposes
    private void addDummyPost() {
        this.contentArea.add(new PostBox(
        "Test post", 
        "This is a test post",
        "1191bb2a-8870-4d3d-87f2-8c4ec522d793", 
        homepage, homepageController, postController).getPostBox());
    }
}
