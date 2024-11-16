package entity;

import java.time.LocalDateTime;
import java.util.List;

/** 
 * Represents a comment.
 */
public class Comment extends ForumEntry {
    private List<Comment> replies;

    /**
     * Creates a comment.
     * @param replies - the replies to this comment.
     * See ForumEntry for other parameters
     */
    public Comment(String entryID, User author, Content content, LocalDateTime postedDate,
                   LocalDateTime lastModifiedDate, int likes, int dislikes, List<Comment> replies) {
        super(entryID, author, content, postedDate, lastModifiedDate, likes, dislikes);
        this.replies = replies;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}
