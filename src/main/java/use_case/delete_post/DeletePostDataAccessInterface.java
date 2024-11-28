package use_case.delete_post;

/**
 * Interface for data access operations related to deleting a post.
 */
public interface DeletePostDataAccessInterface {

    /**
     * Deletes a post from the database.
     * @param postID the ID of the post to delete.
     */
    void deletePost(String postID);

    /**
     * Checks if a post exists in the database.
     * @param postId the ID of the post to check.
     * @return true if the post exists, false otherwise.
     */
    boolean existsByID(String postId);
}

