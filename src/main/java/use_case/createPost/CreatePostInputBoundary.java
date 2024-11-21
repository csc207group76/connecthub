package use_case.createPost;


public interface CreatePostInputBoundary {

    /**
     * Executes the createPost use case.
     * @param createPostInputData the input data
     */
    void createPost(CreatePostInputData createPostInputData);
}