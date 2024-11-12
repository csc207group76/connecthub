package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NormalComment {
    private String commentId;          // Unique identifier for the comment
    private String entryId;            // Identifier for the entry to which the comment belongs
    private String content;
    private User author;
    private LocalDateTime timestamp;
    private int likes;                 // Number of likes
    private int dislikes;              // Number of dislikes
    private ModeratorUser moderator;   // Moderator associated with this comment, if applicable
    private ArrayList<NormalComment> replies;

    public NormalComment(String commentId, String entryId, String content, User author, ModeratorUser moderator) {
        this.commentId = commentId;
        this.entryId = entryId;
        this.content = content;
        this.author = author;
        this.timestamp = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        this.moderator = moderator;
        this.replies = new ArrayList<>();
    }

    public NormalComment(String content, String author) {
    }

    // Getters and Setters for the new fields
    public String getCommentId() {
        return commentId;
    }

    public String getEntryId() {
        return entryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public ModeratorUser getModerator() {
        return moderator;
    }

    public void setModerator(ModeratorUser moderator) {
        this.moderator = moderator;
    }

    public ArrayList<NormalComment> getReplies() {
        return replies;
    }
    
}
