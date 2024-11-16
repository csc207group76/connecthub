package use_case.DeleteComment;

import entity.Comment;

/**
 * Interface for data access operations related to deleting a comment
 */
public interface DeleteCommentDataAccessInterface {

    /**
     * Deletes a comment from the data store by its ID.
     *
     * @param commentId The ID of the comment to be deleted.
     */
    void deleteComment(String commentId);

    /**
     * Finds a comment by its ID
     *
     * @param commentId The ID of the comment to find.
     * @return The comment if found, or null if not.
     */
    Comment existsCommentById(String commentId);
}
