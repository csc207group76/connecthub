package use_case.delete_post;

/**
 * Interface for data access operations related to deleting a post.
 */
public interface DeletePostDataAccessInterface {

    /**
     * Deletes a post from the database.
     *
     * @param postId the ID of the post to delete.
     */
    void deletePost(String postId);

    /**
     * Checks if a post exists in the database.
     *
     * @param postId the ID of the post to check.
     * @return true if the post exists, false otherwise.
     */
    boolean existsByID(String postId);

    /**
     * Retrieves the author ID of a given post.
     *
     * @param postId the ID of the post.
     * @return the author ID of the post, or null if the post does not exist.
     */
    String getPostAuthorId(String postId);

    String getPostCategory(String postId);
}
