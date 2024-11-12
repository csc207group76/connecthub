package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for creating a post.
 */
public class PostFactory {
    /**
     * Creates a post.
     * See Post and ForumEntry for the meaning of the parameters.
     * @return the Post object created.
     */
    public Post createPost(User author, String content, String attachmentPath, String fileType, 
                           String postTitle, String category) {
        Content postContent = new PostContent(content, attachmentPath, fileType);
        String entryID = generateID();
        List<Comment> comments = new ArrayList<Comment>();
        Post post = new Post(entryID, author, postContent, LocalDateTime.now(), null, 
                             0, 0, postTitle, comments, category);
        return post;
    }

    private String generateID() {
        // TODO generate ID using java.util.UUID and check if it exists in the database?
        return "";
    }
}
