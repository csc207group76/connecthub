package use_case.createComment;

public interface CreateCommentOutputBoundary {
    /**
     * Prepares the success view for the CreateComment Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreateCommentOutputData outputData);

    /**
     * Prepares the failure view for the CreateComment Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
