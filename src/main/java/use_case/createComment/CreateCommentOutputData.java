package use_case.createComment;

import entity.User;

public class CreateCommentOutputData {

    private final String entryID;
    private final User author;
    private final String content;
    private final String commentDate;
    private final boolean creationSuccessful;

    public CreateCommentOutputData(String entryID, User author, String content,
                                   String commentDate, boolean creationSuccessful) {
        this.entryID = entryID;
        this.author = author;
        this.content = content;
        this.commentDate = commentDate;
        this.creationSuccessful = creationSuccessful;
    }

    public String getEntryID() {
        return entryID;
    }

    public User getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public boolean isCreationSuccessful() {
        return creationSuccessful;
    }
}
