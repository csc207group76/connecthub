package use_case.DeleteComment;

/**
 * The output boundary for the Delete Comment Use Case.
 */
public interface DeleteCommentOutputBoundary {


    /**
     * Prepares the success view for the Delete Comment Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DeleteCommentOutputData outputData);
    /**
     * Prepares the failure view for the Delete Comment Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
    /**
     * Switches to the Comment View.
     */
    void switchToDeleteCommentView();
    // for doing at a later date


}