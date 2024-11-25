package use_case.delete_post;

/**
 * The output boundary for the Delete Post Use Case
 */
public interface DeletePostOutputBoundary {


    /**
     * Prepares the success view for the Delete Post Use Case.
     * @param outputData the output data.
     */
    void prepareSuccessView(DeletePostOutputData outputData);
    /**
     * Prepares the failure view for the Delete Post Use Case.
     * @param errorMessage the explanation of the failure
     */

    void prepareFailView(String errorMessage);
    /**
     * Switches to the Post View.
     */

    void switchToDeletePostView();
    // TODO for doing at a later date


}
