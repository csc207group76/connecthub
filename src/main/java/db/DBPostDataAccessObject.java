package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Post;
import use_case.getpost.GetPostDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing post data.
 * This implementation does NOT persist data between runs of the program.
 */
public class DBPostDataAccessObject implements GetPostDataAccessInterface {

    private final Map<String, Post> posts = new HashMap<>();

    @Override
    public Post getPostByEntryID(String postId) {
        return posts.get(postId);
    }

    @Override
    public List<Post> getAllPostsByUserID(String userId) {
        final List<Post> userPosts = new ArrayList<>();
        for (Post post : posts.values()) {
            if (post.getAuthor().getName().equals(userId)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    /**
     * Save a post.
     * @param post the Post to save
     */
    public void savePost(Post post) {
        posts.put(post.getEntryID(), post);
    }

    /**
     * Delete a post.
     * @param entryID the entryID of the Post to delete
     * @return true if the post with entryID was deleted; false if such a post does not exist
     */
    public boolean deletePost(String entryID) {
        return posts.remove(entryID) != null;
    }
}
