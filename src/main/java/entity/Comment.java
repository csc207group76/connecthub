package entity;

import java.time.LocalDateTime;

public class Comment extends ForumEntry {
    private Comment[] replies;

    public Comment(String entryID, User author, Content content, LocalDateTime postedDate,
                   LocalDateTime editDate, int likes, int dislikes, Comment[] replies) {
        super(entryID, author, content, postedDate, editDate, likes, dislikes);
        this.replies = replies;
    }

    public Comment[] getReplies() {
        return replies;
    }

    public void setReplies(Comment[] replies) {
        this.replies = replies;
    }
}
