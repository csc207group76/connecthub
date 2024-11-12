package entity;

import java.time.LocalDateTime;
import java.util.List;

public class Comment extends ForumEntry {
    private List<Comment> replies;

    public Comment(String entryID, User author, Content content, LocalDateTime postedDate,
                   LocalDateTime getLastModifiedDate, int likes, int dislikes, List<Comment> replies) {
        super(entryID, author, content, postedDate, getLastModifiedDate, likes, dislikes);
        this.replies = replies;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }
}
