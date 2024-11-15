package use_case.CreateComment;

import entity.Content;
import entity.User;
import entity.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateCommentInputData {
    private final String commentId;
    private final User author;
    private final Content content;
    private final Date timestamp;
    private final int likes;
    private final int dislikes;
    private final List<Comment> replies = new ArrayList<>();

    /**
     * Constructor for CreateCommentInputData.
     * Note: commentId generation might be handled in the database layer later.
     */
    public CreateCommentInputData(String commentId, User author,
                                  Content content, Date timestamp, int likes,
                                  int dislikes) {
        this.commentId = commentId;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public String getContent() {
        return this.content.getBody();
    }

    public String getAttachmentPath() {
        return this.content.getAttachmentPath();
    }

    public String getFileType() {
        return this.content.getFileType();
    }

    // TODO :check if our implementation of comment works for this
    public String getCommentId() {
        return commentId;
    }

    public User getAuthor() {
        return author;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }


    public List<Comment> getComments() {
        return replies;
    }


}
