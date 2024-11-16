package use_case.DeleteComment;

/**
 * This interface for the DeleteComment use case.
 */
public interface DeleteCommentInputBoundary {

    /**
     * Executes the delete comment use case.
     * @param DeleteCommentInputData the input data for the delete comment operation,
     *        typically containing the ID of the comment to be deleted.
     */
    void deleteComment(DeleteCommentInputData DeleteCommentInputData);

}
