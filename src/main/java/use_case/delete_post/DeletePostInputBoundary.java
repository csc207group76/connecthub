package use_case.delete_post;

/**
 * This is the interface for the DeletePost use case.
 */
public interface DeletePostInputBoundary {

    /**
     * Retrieves the author ID of the given post.
     *
     * @param postId the ID of the post whose author ID is being fetched
     * @return the author ID of the post
     */
    String getAuthorId(String postId);

    /**
     * Executes the delete post use case.
     *
     * @param deletePostInputData the input data for the use case,
     *                            typically containing the ID of the post to be deleted
     */
    void deletePost(DeletePostInputData deletePostInputData);

    /**
     * Switches to the home page view after the delete operation.
     */
    void switchToHomePageView();
}
