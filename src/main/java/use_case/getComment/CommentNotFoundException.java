package use_case.getComment;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(String entryID) {
        super("Comment with entryID " + entryID + " not found.");
    }
}
