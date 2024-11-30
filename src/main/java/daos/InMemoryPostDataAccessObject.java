package daos;

import entity.Post;
import use_case.create_post.CreatePostDataAccessInterface;

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
    public void createPost(Post post) {
        // Ensure the author of the post exists in the system
        if (!users.existsByID(post.getAuthor())) {
            throw new IllegalArgumentException("Author does not exist");
        }

        // Add the post to the posts map and associate it with the user
        posts.put(post.getAuthor(), post);
        userPosts.computeIfAbsent(post.getAuthor(), k -> new ArrayList<>()).add(post.getAuthor());
    }


    public boolean existsByTitle(String title) {
        return posts.values().stream()
                .anyMatch(post -> post.getPostTitle().equals(title));
    }
}
