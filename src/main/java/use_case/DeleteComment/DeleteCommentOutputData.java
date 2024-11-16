package use_case.DeleteComment;

/**
 * This class contains the output data for the deletion of a comment .
 *
 */
public class DeleteCommentOutputData {

    private final String commentId;
    private final boolean deletionSuccessful;

    /**
     * Constructs a DeleteCommentOutputData object with the specified comment ID
     * and the result of the deletion attempt.
     *
     * @param commentId The ID of the comment being deleted
     * @param deletionSuccessful A boolean indicating if the deletion operation Successful
     */
    public DeleteCommentOutputData(String commentId, boolean deletionSuccessful) {
        this.commentId = commentId;
        this.deletionSuccessful = deletionSuccessful;
    }

    /**
     * Returns whether the deletion of the comment Successful.
     *
     * @return true if the deletion Successful, false if it was successful
     */
    public boolean isDeletionSuccessful() {
        return deletionSuccessful;
    }

    /**
     * Returns the ID of the comment that was attempted to be deleted.
     *
     * @return the ID of the comment
     */
    public String getCommentId() {
        return commentId;
    }
}
