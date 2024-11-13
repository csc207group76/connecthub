package use_case.CreateComment;

import entity.ModeratorUser;
import entity.User;
import entity.NormalComment;

import java.util.ArrayList;
import java.util.Date;

public class CreateCommentInputData {
    private final String commentId;
    private final String entryId;
    private final User author;
    private final String content;
    private final Date timestamp;
    private final int likes;
    private final int dislikes;
    private final ModeratorUser moderator;
    private final ArrayList<NormalComment> comments = new ArrayList<NormalComment>();

    // dont pass in id
    public CreateCommentInputData(String username, String password,
                                  String repeatPassword, String commentId,
                                  String entryId, User author, String content,
                                  Date timestamp, int likes, int dislikes,
                                  ModeratorUser moderator) {
        this.commentId = commentId;
        this.entryId = entryId;
        this.author = author;

        this.content = content;
        this.timestamp = timestamp;
        this.likes = likes;
        this.dislikes = dislikes;
        this.moderator = moderator;
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
    User getAuthor() {
        return author;
    }
    Date getTimestamp() {
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
