package use_case.createPost;

public interface CreatePostOutputBoundary {

    /**
     * Prepares the success view for the CreatePost Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CreatePostOutputData outputData);

    /**
     * Prepares the failure view for the CreatePost Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);


    // TODO: Switch to some other view?


}