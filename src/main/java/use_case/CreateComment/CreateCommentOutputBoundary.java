package use_case.CreateComment;

/**
 * The output boundary for the Create Comment Use Case.
 */
public interface CreateCommentOutputBoundary {

    /**
     * Prepares the success view for the Create Comment Use Case.
     * @param outputData the output data.
     */
    void prepareSuccessView(CreateCommentOutputData outputData);

    /**
     * Prepares the failure view for the Create Comment Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the create Comment View.
     */
    void switchToCreateCommentView();
    // for doing at a later date
}