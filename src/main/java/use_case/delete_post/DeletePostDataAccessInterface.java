package use_case.delete_post;

/**
 * Interface for data access operations related to deleting a post.
 */
public interface DeletePostDataAccessInterface {

    void deletePost(String postID);

    boolean existsByID(String postId);
}

