package use_case.createComment;


public interface CreateCommentInputBoundary {

    /**
     * Executes the createComment use case.
     * @param createCommentInputData the input data
     */
    void createComment(CreateCommentInputData createCommentInputData);
}
