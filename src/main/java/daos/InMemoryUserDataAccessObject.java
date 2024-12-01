package daos;

import entity.User;
import use_case.create_post.CreatePostDataAccessInterface;
import entity.Post;
import use_case.signup.SignupDataAccessInterface;

import java.util.*;

public class InMemoryUserDataAccessObject implements SignupDataAccessInterface{

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Post> posts = new HashMap<>(); // Stores posts by ID
    private final Map<String, List<String>> userPosts = new HashMap<>(); // Maps users to their post IDs

    private String currentUser;

    @Override
    public boolean existsByUsername(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public boolean existsByID(String userID) {
        return users.values().stream()
                .anyMatch(user -> user.getUserID().equals(userID));
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
        userPosts.putIfAbsent(user.getUsername(), new ArrayList<>()); // Initialize the user's post list
    }

    @Override
    public void setCurrentUser(User user) {

    }
}
