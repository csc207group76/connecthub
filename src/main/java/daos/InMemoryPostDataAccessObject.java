package daos;

import entity.Post;
import entity.User;
import use_case.createPost.CreatePostDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPostDataAccessObject implements CreatePostDataAccessInterface {

    private final InMemoryUserDataAccessObject users;
    private final Map<String, Post> posts = new HashMap<>(); // Stores posts by ID
    private final Map<String, List<String>> userPosts = new HashMap<>(); // Maps users to their post IDs

    private String currentUser;

    public InMemoryPostDataAccessObject(InMemoryUserDataAccessObject userRepository) {
        this.users = userRepository;  // Link to the user repository
    }


    @Override
    public void create(Post post) {
        // Ensure the author of the post exists in the system
        if (!users.existsByID(post.getAuthor().getUserID())) {
            throw new IllegalArgumentException("Author does not exist");
        }

        // Add the post to the posts map and associate it with the user
        posts.put(post.getAuthor().getUserID(), post);
        userPosts.computeIfAbsent(post.getAuthor().getUserID(), k -> new ArrayList<>()).add(post.getAuthor().getUserID());
    }

    @Override
    public boolean existsByTitle(String title) {
        return posts.values().stream()
                .anyMatch(post -> post.getPostTitle().equals(title));
    }
}
