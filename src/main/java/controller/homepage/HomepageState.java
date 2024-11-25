package controller.homepage;

import java.util.ArrayList;
import java.util.List;

import entity.Post;

/**
 * Represents the state of the HomePage view.
 */
public class HomepageState {
    private String currentUser = "";
    private String currentUserError;
    private List<Post> posts = new ArrayList<>();
    private List<Post> postsError;
    
    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUserError() {
        return currentUserError;
    }

    public void setCurrentUserError(String currentUserError) {
        this.currentUserError = currentUserError;
    }

    public List<Post> getPostsError() {
        return postsError;
    }

    public void setPostsError(List<Post> postsError) {
        this.postsError = postsError;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
