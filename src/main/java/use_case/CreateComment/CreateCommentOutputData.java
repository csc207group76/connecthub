package use_case.CreateComment;

import entity.Comment;
import entity.Content;
import entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateCommentOutputData {
    private final String commentId;
    private final User author;
    private final Content content;
    private final LocalDateTime timestamp;
    private final int likes;
    private final int dislikes;
    private final ArrayList<Comment> comments = new ArrayList<>();
    private final boolean creationSuccessful;

    public CreateCommentOutputData(String commentId, User author, Content content,
                                   LocalDateTime timestamp, int likes, int dislikes,
                                   boolean creationSuccessful) {
        this.commentId = commentId;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.dislikes = dislikes;
        this.creationSuccessful = creationSuccessful;
    }

    public boolean isCreationSuccessful() {
        return !creationSuccessful;
    }

    public Content getContent() {
        return this.content;
    }

    // TODO :check if our implementation of comment works for this
    public String getCommentId() {
        return commentId;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
