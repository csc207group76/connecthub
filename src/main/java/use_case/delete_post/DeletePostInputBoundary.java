package use_case.delete_post;

/**
 * This interface for the DeletePost use case.
 */
public interface DeletePostInputBoundary {

    /**
     * Executes the delete Post use case.
     * @param DeletePostInputData the input data for the delete Post operation,
     *        typically containing the ID of the Post to be deleted
     */
    void deletePost(DeletePostInputData DeletePostInputData);
}
