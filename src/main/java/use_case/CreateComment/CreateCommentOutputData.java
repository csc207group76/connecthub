package use_case.CreateComment;

import entity.NormalComment;
import entity.ModeratorUser;
import entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Output Data for the Signup Use Case.
 */
public class CreateCommentOutputData {
    private final String commentId;
    private final String entryId;
    private final User author;
    private final String content;
    private final LocalDateTime timestamp;
    private final int likes;
    private final int dislikes;
    private final ModeratorUser moderator;
    private final ArrayList<NormalComment> comments = new ArrayList<NormalComment>();
    private Boolean CreationFailed;


    public CreateCommentOutputData(String commentId,
                                   String entryId, User author, String content,
                                   LocalDateTime timestamp, int likes, int dislikes,
                                   ModeratorUser moderator, boolean useCaseFailed) {
        this.commentId = commentId;
        this.entryId = entryId;
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.dislikes = dislikes;
        this.moderator = moderator;
        this.CreationFailed = useCaseFailed;
    }


    public boolean isCreationSuccessful() {
        return CreationFailed;
    }

    String getContent() {
        return this.content;
    }

    String getCommentId() {
        return commentId;
    }

    String getEntryId() {
        return entryId;
    }
    String getAuthor() {
        return author.getName();
    }
    LocalDateTime getTimestamp() {
        return timestamp;
    }
    int getLikes() {
        return likes;
    }
    int getDislikes() {
        return dislikes;
    }
    ModeratorUser getModerator() {
        return moderator;
    }

    ArrayList<NormalComment> getComments() {
        return comments;
    }



}
