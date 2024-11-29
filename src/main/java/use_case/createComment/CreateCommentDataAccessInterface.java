package use_case.createComment;

import entity.Comment;
import org.json.JSONObject;

public interface CreateCommentDataAccessInterface {

    /**
     * Creates a comment.
     *
     * @param comment The comment to be created.
     */
    void createComment(Comment comment);

    /**
     * Finds a comment by its ID
     *
     * @param commentId The ID of the comment to find.
     * @return The comment if found, or null if not.
     */
    JSONObject getCommentByID(String commentId);
}
