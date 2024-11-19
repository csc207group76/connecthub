package use_case.getpost;

/**
 * The output boundary for the Get Post Use Case.
 */
public interface GetPostOutputBoundary {
    /**
     * Prepares the success view for the Get Post Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GetPostOutputData outputData);

    /**
     * Prepares the failure view for the Get Post Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
